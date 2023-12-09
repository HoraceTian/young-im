package cn.young.im.plugin.api;

import cn.young.im.plugin.api.context.YoungPluginContext;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 判断插件是否可用
 * @date 2023/11/24
 */
public interface PluginAvailableFilter {
    /**
     * 判断是否可用
     */
    boolean isAvailable(YoungPluginContext context);
}

