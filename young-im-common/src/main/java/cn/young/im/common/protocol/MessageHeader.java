package cn.young.im.common.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MessageHeader
 * @Description 消息头一共18字节
 * @date 2023/11/15 13:38
 * @Author yanceysong
 * @Version 1.0
 */
@Data
public class MessageHeader implements Serializable {
    /**
     * 消息操作指令(2字节) 十六进制
     */
    private Short command;
    /**
     * 消息版本号(2字节)
     */
    private Short version;
    /**
     * 客户端端类型 ios mac win Android(2字节)
     */
    private Short clientType;
    /**
     * 应用ID(4字节)
     */
    private Integer appId;
    /**
     * 消息类型 文本、图片、文件、emoji等...(2字节)
     */
    private Short messageType;
    /**
     * 消息序列化的协议 0x0:Json,0x1:ProtoBuf,0x2:Xml,默认:0x0 (2字节)
     */
    private Short messageProtocol;
    /**
     * 包体长度(4字节)
     */
    private Integer messageBodyLength;
}