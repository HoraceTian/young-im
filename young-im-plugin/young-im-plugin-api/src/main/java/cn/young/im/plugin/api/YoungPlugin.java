package cn.young.im.plugin.api;

import cn.young.im.plugin.api.context.YoungContext;
import cn.young.im.plugin.api.dto.response.YoungResult;
import cn.young.im.plugin.api.dto.rule.PluginGroup;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description Young im 插件
 * @date 2023/11/24
 */
public interface YoungPlugin {

    /**
     * 排序优先级
     */
    int getOrder();

    /**
     * 获取插件组
     */
    PluginGroup acquireGroup();

    /**
     * 获取插件名称
     */
    default String acquireName() {
        return "";
    };

    /**
     * 业务执行点
     */
    YoungResult execute(YoungContext context);

    /**
     * 判断是否可用
     */
    boolean isAvailable(YoungContext context);
}
