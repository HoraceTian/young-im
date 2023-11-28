package cn.young.im.plugin.base;

import cn.young.im.plugin.api.YoungPlugin;
import cn.young.im.plugin.api.context.YoungContext;
import cn.young.im.plugin.api.dto.response.YoungPluginResult;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 预留模板
 * @date 2023/11/26
 */
public abstract class AbstractSimpleYoungPlugin implements YoungPlugin {

    @Override
    public YoungPluginResult execute(YoungContext context) {
        return doExecute(context);
    }

    /**
     * 子类实现
     */
    public abstract YoungPluginResult doExecute(YoungContext context);
}
