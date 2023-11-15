package cn.young.im.common.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MessageHeader
 * @Description 消息头
 * @date 2023/11/15 13:38
 * @Author yanceysong
 * @Version 1.0
 */
@Data
public class MessageHeader implements Serializable {
    /**
     * 消息操作指令(4字节) 十六进制
     */
    private Integer command;
    /**
     * 4字节 版本号
     */
    private Integer version;
    /**
     * 4字节 端类型
     */
    private Integer clientType;
    /**
     * 应用ID(4字节)
     */
    private Integer appId;
    /**
     * 数据解析类型(4字节) 和具体业务无关
     * 后续根据解析类型解析data数据 0x0:Json,0x1:ProtoBuf,0x2:Xml,默认:0x0
     */
    private Integer messageType;

    /**
     * 4字节 imei长度
     */
    private Integer imeiLength;

    /**
     * 4字节 包体长度
     */
    private int length;

}
