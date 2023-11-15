package cn.young.im.netty.strategy.command.system.command;

import cn.young.im.netty.strategy.command.system.model.CommandExecutionContext;
import cn.young.im.netty.strategy.command.system.BaseSystemCommandStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName LogoutCommand
 * @Description
 * @date 2023/4/25 11:20
 * @Author yanceysong
 * @Version 1.0
 */
@Slf4j
@Service
public class LogoutSystemCommand extends BaseSystemCommandStrategy {
    @Override
    public void systemStrategy(CommandExecutionContext commandExecution) {
        log.info(commandExecution.toString());
    }

}
