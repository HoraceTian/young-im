package cn.young.im.netty.handler;

import cn.young.im.common.protocol.Message;
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

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.debug("channel 活跃");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.debug("channel 非活跃,关闭");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理
        log.error(cause.getMessage(), (Object) cause.getStackTrace());
        ctx.close();
    }
}