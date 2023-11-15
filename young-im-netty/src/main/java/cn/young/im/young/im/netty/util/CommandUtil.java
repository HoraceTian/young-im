package cn.young.im.young.im.netty.util;

import cn.young.im.common.protocol.Message;
import cn.young.im.young.im.netty.strategy.command.system.model.CommandExecutionContext;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * @ClassName CommandUtil
 * @Description 指令工具
 * @date 2023/11/15 15:05
 * @Author yanceysong
 * @Version 1.0
 */
@Slf4j
public class CommandUtil {
    /**
     * 采用对象池复用对象，防止在启动项目时 CPU 占用率飙升
     */
    private static final GenericObjectPool<CommandExecutionContext> commandExecutionRequestPool
            = new GenericObjectPool<>(new CommandExecutionFactory());

    /**
     * 根据消息解析出指令信息
     *
     * @param message 消息
     * @return 指令
     */
    public static Integer parseCommand(Message message) {
        return message.getMessageHeader().getCommand();
    }

    /**
     * 从对象池当中拿到一个实体类
     *
     * @param ctx     上下文
     * @param message 消息体
     * @return 指令执行对象上下文
     */
    public static CommandExecutionContext getCommandExecution(ChannelHandlerContext ctx, Message message) {
        CommandExecutionContext commandExecutionContext = null;
        try {
            commandExecutionContext = commandExecutionRequestPool.borrowObject();
            commandExecutionContext.setCtx(ctx);
            commandExecutionContext.setMessage(message);
        } catch (Exception e) {
            log.error("对象池并不存在所需对象，错误原因:", e);
        }
        return commandExecutionContext;
    }

    /**
     * 将一个对象归还到对象池
     *
     * @param executionContext 指令执行对象上下文
     */
    public static void returnCommandExecution(CommandExecutionContext executionContext) {
        commandExecutionRequestPool.returnObject(executionContext);
    }

    /**
     * CommandExecution 对象工厂
     */
    private static class CommandExecutionFactory extends BasePooledObjectFactory<CommandExecutionContext> {
        @Override
        public CommandExecutionContext create() throws Exception {
            return new CommandExecutionContext();
        }

        @Override
        public PooledObject<CommandExecutionContext> wrap(CommandExecutionContext obj) {
            return new DefaultPooledObject<>(obj);
        }
    }
}
