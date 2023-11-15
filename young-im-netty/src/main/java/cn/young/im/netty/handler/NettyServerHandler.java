package cn.young.im.netty.handler;

import cn.hutool.extra.spring.SpringUtil;
import cn.young.im.common.protocol.Message;
import cn.young.im.netty.strategy.command.system.SystemCommandStrategy;
import cn.young.im.netty.strategy.command.system.command.factory.CommandFactory;
import cn.young.im.netty.strategy.command.system.model.CommandExecutionContext;
import cn.young.im.netty.util.CommandUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName NettyServerHandler
 * @Description 消息处理
 * @date 2023/11/15 10:19
 * @Author yanceysong
 * @Version 1.0
 */
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) {
        // 解析指令
        Integer command = CommandUtil.parseCommand(message);
        // 拿到指令工厂进行策略模式
        CommandFactory commandFactory = SpringUtil.getBean(CommandFactory.class);
        SystemCommandStrategy commandStrategy = commandFactory.getCommandStrategy(command);
        CommandExecutionContext commandExecutionContext = null;
        try {
            // 从对象池中获取 CommandExecution 对象
            commandExecutionContext = CommandUtil.getCommandExecution(ctx, message);
            if (commandStrategy != null) {
                // 执行策略
                commandStrategy.systemStrategy(commandExecutionContext);
            } else {
                log.info("未知的策略..{}", message);
            }
        } finally {
            // 将对象归还给对象池
            if (commandExecutionContext != null) {
                CommandUtil.returnCommandExecution(commandExecutionContext);
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.debug("channel 活跃");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.debug("channel 非活跃");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理
        log.error(cause.getMessage(), (Object) cause.getStackTrace());
    }
}