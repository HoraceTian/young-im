package cn.young.im.plugin.user;

import cn.young.im.plugin.api.context.YoungContext;
import cn.young.im.plugin.api.dto.response.YoungPluginResult;
import cn.young.im.plugin.api.dto.rule.PluginGroup;
import cn.young.im.plugin.base.AbstractSimpleYoungPlugin;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 用户相关操作处理插件
 * @date 2023/11/26
 */
public class UserProcessPlugin extends AbstractSimpleYoungPlugin {
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public PluginGroup acquireGroup() {
        return PluginGroup.MESSAGE_STRATEGY;
    }

    @Override
    public boolean isAvailable(YoungContext context) {
        return false;
    }

    @Override
    public YoungPluginResult doExecute(YoungContext context) {
        System.out.println("覆盖的插件实现");
        return null;
    }
}
