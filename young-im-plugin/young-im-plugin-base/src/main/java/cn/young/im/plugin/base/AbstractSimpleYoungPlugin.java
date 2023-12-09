package cn.young.im.plugin.base;

import cn.young.im.plugin.api.YoungPlugin;
import cn.young.im.plugin.api.context.YoungPluginContext;
import cn.young.im.plugin.api.dto.response.YoungPluginResult;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 预留模板
 * @date 2023/11/26
 */
public abstract class AbstractSimpleYoungPlugin implements YoungPlugin {

    @Override
    public YoungPluginResult execute(YoungPluginContext context) {
        // 如果执行被中断或者插件不可用就忽略执行
        if (context.isInterrupt() || !isAvailable(context)) return context.getYoungPluginResult();
        return doExecute(context);
    }

    /**
     * 子类实现
     */
    public abstract YoungPluginResult doExecute(YoungPluginContext context);
}
