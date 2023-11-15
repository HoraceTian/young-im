package cn.young.im.netty.strategy.login.impl;


import cn.young.im.common.model.user.UserClientDTO;
import cn.young.im.netty.strategy.login.LoginStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName ThreeClientLoginStatus
 * @Description  允许三个客户端在线
 * @date 2023/4/27 13:56
 * @Author yanceysong
 * @Version 1.0
 */
@Service
@Slf4j
public class ThreeClientLoginStatus extends LoginStatus {
    @Override
    public void switchStatus(LoginStatus status) {
        context.setStatus(status);
    }

    @Override
    public void handleUserLogin(UserClientDTO userClientDTO) {

    }
}
