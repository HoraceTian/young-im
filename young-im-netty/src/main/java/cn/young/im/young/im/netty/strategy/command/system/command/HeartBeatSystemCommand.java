package cn.young.im.young.im.netty.strategy.command.system.command;

import cn.young.im.young.im.netty.strategy.command.system.BaseSystemCommandStrategy;
import cn.young.im.young.im.netty.strategy.command.system.model.CommandExecutionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName HeartBeatSystemCommand
 * @Description 心跳处理
 * @date 2023/4/25 14:46
 * @Author yanceysong
 * @Version 1.0
 */
@Slf4j
@Component
public class HeartBeatSystemCommand extends BaseSystemCommandStrategy {
    @Override
    public void systemStrategy(CommandExecutionContext commandExecution) {
        log.info(commandExecution.toString());
    }
}
