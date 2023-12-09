package cn.young.im.netty.strategy.login;

import cn.young.im.common.model.user.UserClientDTO;
import cn.young.im.netty.strategy.login.impl.AllClientLoginStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName LoginContext
 * @Description
 * @date 2023/4/27 13:51
 * @Author yanceysong
 * @Version 1.0
 */
@Service
@Slf4j
public class LoginContext {
    private LoginStatus status;

    public LoginContext() {
        status = new AllClientLoginStatus();
        status.setContext(this);
    }

    public void setStatus(LoginStatus status) {
        this.status = status;
        this.status.setContext(this);
    }

    public void handleUserLogin(UserClientDTO userClientDTO) {
        status.handleUserLogin(userClientDTO);
    }
}
