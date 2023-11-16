package cn.young.im.common.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MessageBody
 * @Description 消息体
 * @date 2023/11/15 13:40
 * @Author yanceysong
 * @Version 1.0
 */
@Data
public class MessageBody implements Serializable {
    /**
     * 发送方id
     */
    private String sendId;
    /**
     * 接收方
     */
    private String receiveId;
    /**
     * 消息ID
     */
    private String messageId;
    /**
     * 设备的标识，不同设备可以选择不同的标识方式。例如移动端可以选择imei号...
     */
    private String identification;

    /**
     * 业务数据对象
     */
    private Object data;
}
