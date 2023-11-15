package cn.young.im.common.enums.command;

import cn.young.im.common.enums.KeyValueEnum;

/**
 * @ClassName SystemCommand
 * @Description
 * @date 2023/11/15 14:32
 * @Author yanceysong
 * @Version 1.0
 */
public enum SystemCommand implements KeyValueEnum<Integer, String> {
    /**
     * 登录 9000 --> 0x2328
     */
    LOGIN(0x2328, "login"),
    /**
     * 登出 9003 --> 0x232b
     */
    LOGOUT(0x232b, "logout"),
    /**
     * 心跳 9999 --> 0x270f
     */
    HEARTBEAT(0x270f, "heartbeat"),
    /**
     * 消息发送 9005 --> 0x232d
     */
    SEND_MSG(0x232d, "send message"),
    /**
     * 下线通知 用于多端互斥 9002 --> 0x232a
     */
    MULTI_LOGOUT(0x232a, "multi logout");;
    private final Integer command;
    private final String description;

    SystemCommand(Integer command, String description) {
        this.command = command;
        this.description = description;
    }

    public Integer getCommand() {
        return getCode();
    }

    @Override
    public Integer getCode() {
        return command;
    }

    @Override
    public String getDesc() {
        return description;
    }
}
