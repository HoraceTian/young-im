package cn.young.im.plugin.api.context;

import cn.young.im.plugin.api.dto.rule.PluginSelectRule;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description Young IM 插件上下文
 * @date 2023/11/24
 */
public class YoungContext {

    /**
     * 插件选择规则
     */
    private PluginSelectRule rule;


    /**
     * 补充用户信息进行付费验证
     */
    private String userInfo;

}
