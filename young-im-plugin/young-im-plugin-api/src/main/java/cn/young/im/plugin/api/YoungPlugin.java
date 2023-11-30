package cn.young.im.plugin.api;

import cn.young.im.plugin.api.context.YoungPluginContext;
import cn.young.im.plugin.api.dto.response.YoungPluginResult;
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
     * 获取插件的身份 id or name
     */
    String getIdentity();

    /**
     * 获取插件组
     */
    PluginGroup acquireGroup();

    /**
     * 获取插件名称
     */
    default String acquireName() {
        return "";
    }

    ;

    /**
     * 业务执行点
     */
    YoungPluginResult execute(YoungPluginContext context);

    /**
     * 判断是否可用
     */
    boolean isAvailable(YoungPluginContext context);
}
