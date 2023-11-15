package cn.young.im.netty.strategy.command.system;


import cn.young.im.netty.strategy.command.system.model.CommandExecutionContext;

/**
 * @ClassName CommandStrategy
 * @Description
 * @date 2023/4/25 10:24
 * @Author yanceysong
 * @Version 1.0
 */
public interface SystemCommandStrategy {

    /**
     * 系统命令执行策略接口
     *
     * @param commandExecutionContext 执行指令需要的内容
     */
    void systemStrategy(CommandExecutionContext commandExecutionContext);
}
