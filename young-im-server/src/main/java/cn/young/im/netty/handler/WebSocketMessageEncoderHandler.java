package cn.young.im.netty.handler;

import cn.young.im.common.protocol.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @ClassName WebSocketMessageEncoderHandler
 * @Description websocket编码器
 * @date 2023/11/15 10:19
 * @Author yanceysong
 * @Version 1.0
 */
@Slf4j
public class WebSocketMessageEncoderHandler extends MessageToMessageEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, List<Object> out) {
        log.info(message.toString());
    }
}