package cn.young.im.netty.strategy.command.system.command;

import cn.young.im.netty.strategy.command.system.model.CommandExecutionContext;
import cn.young.im.netty.strategy.command.system.BaseSystemCommandStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName P2PMsgCommand
 * @Description TCP 层校验消息发送方合法性
 * @date 2023/5/16 11:25
 * @Author yanceysong
 * @Version 1.0
 */
@Slf4j
@Component
public class P2PMsgSystemCommand extends BaseSystemCommandStrategy {

    @Override
    public void systemStrategy(CommandExecutionContext commandExecution) {
        log.info(commandExecution.toString());
    }

}