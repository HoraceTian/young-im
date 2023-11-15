package cn.young.im.young.im.netty.strategy.command.system.command.factory;

import cn.young.im.common.enums.command.SystemCommand;
import cn.young.im.young.im.netty.strategy.command.system.SystemCommandStrategy;
import cn.young.im.young.im.netty.strategy.command.system.command.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName CommandFacotry
 * @Description 指令工厂，策略模式
 * 使用单例模式防止每次读取 channel 都需要初始化 CommandFactory, 所导致的 CPU 飙升
 * @date 2023/4/25 10:23
 * @Author yanceysong
 * @Version 1.0
 */
@Service
@Slf4j
public class CommandFactory {
    /**
     * 命令维护策略组
     */
    private final Map<Integer, SystemCommandStrategy> commandStrategyMap = new ConcurrentHashMap<>();
    @Resource
    private LoginSystemCommand loginCommand;
    @Resource
    private LogoutSystemCommand logoutCommand;
    @Resource
    private HeartBeatSystemCommand heartBeatSystemCommand;
    @Resource
    private P2PMsgSystemCommand p2PMsgCommand;
    @Resource
    private GroupMsgSystemCommand groupMsgCommand;

    public void init() {
        commandStrategyMap.put(SystemCommand.LOGIN.getCommand(), loginCommand);
        commandStrategyMap.put(SystemCommand.LOGOUT.getCommand(), logoutCommand);
        commandStrategyMap.put(SystemCommand.HEARTBEAT.getCommand(), heartBeatSystemCommand);
        // todo netty 如何传递给im message层 消息命令策略
//        commandStrategyMap.put(MessageCommand.MSG_P2P.getCommand(), p2PMsgCommand);
//        commandStrategyMap.put(GroupEventCommand.MSG_GROUP.getCommand(), groupMsgCommand);

    }

    public SystemCommandStrategy getCommandStrategy(Integer command) {
        return commandStrategyMap.get(command);
    }
}

