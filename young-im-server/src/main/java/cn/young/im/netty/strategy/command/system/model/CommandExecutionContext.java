package cn.young.im.netty.strategy.command.system.model;

import cn.young.im.common.protocol.Message;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName CommandExecutionRequest
 * @Description
 * @date 2023/5/16 11:26
 * @Author yanceysong
 * @Version 1.0
 */
@Data
@ToString
public class CommandExecutionContext {

    private ChannelHandlerContext ctx;

    private Message message;
}