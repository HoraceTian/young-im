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
        if (content.readableBytes() < Message.MESSAGE_MIN_LENGTH) {
            return;
        }
        Message message = null;
        try {
            // 获取command
            short command = content.readShort();
            // 获取version
            short version = content.readShort();
            // 获取clientType
            short clientType = content.readShort();
            // 获取appId
            int appId = content.readInt();
            // 获取messageType
            short messageType = content.readShort();
            short messageProtocol = content.readShort();
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
            messageHeader.setMessageProtocol(messageProtocol);
            messageHeader.setVersion(version);
            messageHeader.setMessageType(messageType);
            // 组装message body
            String bodyRawData = new String(bodyByteData, Charset.defaultCharset());
            // todo 目前是json，未来会根据messageProtocol选择不同的序列化方式
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