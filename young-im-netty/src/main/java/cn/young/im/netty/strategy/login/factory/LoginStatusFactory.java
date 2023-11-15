package cn.young.im.netty.strategy.login.factory;

import cn.young.im.common.enums.device.DeviceMultiLoginEnum;
import cn.young.im.common.model.user.UserClientDTO;
import cn.young.im.netty.strategy.login.impl.AllClientLoginStatus;
import cn.young.im.netty.strategy.login.impl.ThreeClientLoginStatus;
import cn.young.im.netty.strategy.login.impl.TwoClientLoginStatus;
import cn.young.im.netty.strategy.login.LoginContext;
import cn.young.im.netty.strategy.login.LoginStatus;
import cn.young.im.netty.strategy.login.impl.OneClientLoginStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName LoginStatusFactory
 * @Description
 * @date 2023/4/27 13:54
 * @Author yanceysong
 * @Version 1.0
 */
@Slf4j
@Service
@Order(Integer.MAX_VALUE)
public class LoginStatusFactory {
    private final LoginContext ctx = new LoginContext();
    private final Map<Integer, LoginStatus> LoginStatusMap = new ConcurrentHashMap<>();
    @Resource
    private OneClientLoginStatus oneClientLoginStatus;
    @Resource
    private TwoClientLoginStatus twoClientLoginStatus;
    @Resource
    private ThreeClientLoginStatus threeClientLoginStatus;
    @Resource
    private AllClientLoginStatus allClientLoginStatus;

    @PostConstruct
    public void init() {
        LoginStatusMap.put(DeviceMultiLoginEnum.ONE.getCode(), oneClientLoginStatus);
        LoginStatusMap.put(DeviceMultiLoginEnum.TWO.getCode(), twoClientLoginStatus);
        LoginStatusMap.put(DeviceMultiLoginEnum.THREE.getCode(), threeClientLoginStatus);
        LoginStatusMap.put(DeviceMultiLoginEnum.ALL.getCode(), allClientLoginStatus);
    }

    /**
     * 上下文存储、路由用户所选择的端同步类型
     *
     * @param status 状态
     */
    public void chooseLoginStatus(Integer status) {
        LoginStatus loginStatus = LoginStatusMap.get(status);
        ctx.setStatus(loginStatus);
    }

    /**
     * 处理用户所选择端同步类型，判断是否需要下线 channel 旧信息
     *
     * @param userClientDTO 上下文
     */
    public void handleUserLogin(UserClientDTO userClientDTO) {
        ctx.handleUserLogin(userClientDTO);
    }
}
