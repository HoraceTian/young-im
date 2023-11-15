package cn.young.im.young.im.netty.strategy.login.impl;


import cn.young.im.common.model.user.UserClientDTO;
import cn.young.im.young.im.netty.strategy.login.LoginStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName AllClientLoginStatus
 * @Description 允许所有客户端在线
 * @date 2023/4/27 13:55
 * @Author yanceysong
 * @Version 1.0
 */
@Service
@Slf4j
public class AllClientLoginStatus extends LoginStatus {
    @Override
    public void switchStatus(LoginStatus status) {
        context.setStatus(status);
    }

    @Override
    public void handleUserLogin(UserClientDTO userClientDTO) {
        // 放权，允许多设备登录，同端之间也不做逻辑处理
    }
}
