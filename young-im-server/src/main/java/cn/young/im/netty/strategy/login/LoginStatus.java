package cn.young.im.netty.strategy.login;

import cn.young.im.common.enums.device.ClientType;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import cn.young.im.common.model.user.UserClientDTO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName LoginStates
 * @Description
 * @date 2023/4/27 13:51
 * @Author yanceysong
 * @Version 1.0
 */
@Service
@Slf4j
public abstract class LoginStatus {
    protected static Map<Integer, String> map = new ConcurrentHashMap<>();

    static {
        //枚举类信息转为map
        for (ClientType c : ClientType.values()) {
            map.put(c.getCode(), c.getDesc());
        }
    }
    protected LoginContext context;


    /**
     * 发送用户下线消息
     * 并不是真正粗暴清除 channel 里的旧信息，因为需要等待数据包停止传输
     * 在服务器行为中，能清除 channel 里旧信息的方式只有 用户登出 Logout 和 心跳超时 Ping-out
     *
     * @param userChannel       用户的channel
     * @param channelClientType 客户端类型
     * @param channelImei       Imei
     * @param userClientDTO               用户信息
     */
    public static void sendMutualLoginMsg(Channel userChannel, Integer channelClientType, String channelImei, UserClientDTO userClientDTO) {

    }

    /**
     * map当中存储了codeType与设备类型的映射
     *
     * @param clientType 类型
     * @return 设备类型
     */
    public static String parseClientType(Integer clientType) {
        return map.get(clientType);
    }

    public void setContext(LoginContext context) {
        this.context = context;
    }

    public abstract void switchStatus(LoginStatus status);

    public abstract void handleUserLogin(UserClientDTO userClientDTO);
}
