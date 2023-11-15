package cn.young.im.netty.handler;

import cn.young.im.common.protocol.Message;
import cn.young.im.common.protocol.MessageBody;
import cn.young.im.common.protocol.MessageHeader;
import com.alibaba.fastjson2.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @ClassName WebSocketMessageDecoderHandler
 * @Description websocket解码器
 * @date 2023/11/15 10:19
 * @Author yanceysong
 * @Version 1.0
 */
@Slf4j
public class WebSocketMessageDecoderHandler extends MessageToMessageDecoder<BinaryWebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext ctx, BinaryWebSocketFrame msg, List<Object> out) {
        ByteBuf content = msg.content();
        if (content.readableBytes() < 28) {
            return;
        }
        Message message = null;
        try {
            // 获取command
            int command = content.readInt();
            // 获取version
            int version = content.readInt();
            // 获取clientType
            int clientType = content.readInt();
            // 获取messageType
            int messageType = content.readInt();
            // 获取appId
            int appId = content.readInt();
            // 获取bodyLen
            int bodyLen = content.readInt();
            if (content.readableBytes() < bodyLen) {
                content.resetReaderIndex();
                return;
            }
            byte[] bodyByteData = new byte[bodyLen];
            content.readBytes(bodyByteData);
            // 组装message header
            MessageHeader messageHeader = new MessageHeader();
            messageHeader.setAppId(appId);
            messageHeader.setClientType(clientType);
            messageHeader.setCommand(command);
            messageHeader.setLength(bodyLen);
            messageHeader.setVersion(version);
            messageHeader.setMessageType(messageType);
            // 组装message body
            String bodyRawData = new String(bodyByteData, Charset.defaultCharset());
            // todo 目前是json，未来可能选择其他高效的方式
            MessageBody messageBody = JSON.parseObject(bodyRawData, MessageBody.class);
            // 组装message
            message = new Message();
            message.setMessageHeader(messageHeader);
            message.setMessageBody(messageBody);
            content.markReaderIndex();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (message == null) {
            return;
        }
        out.add(message);
    }
}