package cn.young.im.common.protocol;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName Message
 * @Description
 * @date 2023/11/15 13:38
 * @Author yanceysong
 * @Version 1.0
 */
@Data
@ToString
public class Message implements Serializable {
    /**
     * 请求头最小字节
     */
    public static int MESSAGE_MIN_LENGTH = 18;
    private MessageHeader messageHeader;
    private MessageBody messageBody;
}
