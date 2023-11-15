package cn.young.im.young.im.netty.strategy.login.impl;

import cn.young.im.common.model.user.UserClientDTO;
import cn.young.im.young.im.netty.strategy.login.LoginStatus;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OneClientLoginStatus
 * @Description 只允许一个客户端在线
 * @date 2023/4/27 13:56
 * @Author yanceysong
 * @Version 1.0
 */
@Service
@Slf4j

public class OneClientLoginStatus extends LoginStatus {
    @Override
    public void switchStatus(LoginStatus status) {
        context.setStatus(status);
    }

    @Override
    public void handleUserLogin(UserClientDTO userClientDTO) {

    }
}
