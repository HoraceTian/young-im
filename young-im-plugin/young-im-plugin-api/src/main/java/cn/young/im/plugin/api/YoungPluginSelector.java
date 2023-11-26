package cn.young.im.plugin.api;

import cn.young.im.plugin.api.context.YoungContext;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 业务插件选择器
 * @date 2023/11/24
 */
public interface YoungPluginSelector {

    /**
     * 实现插件选择规则
     */
    YoungPlugin select(YoungContext context);
}
