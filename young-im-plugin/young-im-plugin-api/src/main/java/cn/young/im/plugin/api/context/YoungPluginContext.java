package cn.young.im.plugin.api.context;

import cn.young.im.common.protocol.Message;
import cn.young.im.plugin.api.dto.response.YoungPluginResult;
import cn.young.im.plugin.api.dto.rule.PluginSelectRule;
import lombok.Data;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description Young IM 插件上下文
 * @date 2023/11/24
 */
@Data
public class YoungPluginContext {
    /**
     * 要执行的业务插件链名字
     */
    private String pluginChainName;
    /**
     * 消息
     */
    private Message message;
    /**
     * 插件选择规则
     */
    private PluginSelectRule rule;
    /**
     * 补充用户信息进行付费验证
     */
    private String userInfo;
    /**
     * 上下文插件执行结果返回值
     */
    private YoungPluginResult youngPluginResult;
    /**
     * 执行时候是否被中断
     */
    private boolean interrupt = false;
}
