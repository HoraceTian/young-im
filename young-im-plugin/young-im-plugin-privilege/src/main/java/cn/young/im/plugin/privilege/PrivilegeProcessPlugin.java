package cn.young.im.plugin.privilege;

import cn.young.im.common.constants.YoungPluginConst;
import cn.young.im.plugin.api.context.YoungPluginContext;
import cn.young.im.plugin.api.dto.response.YoungPluginResult;
import cn.young.im.plugin.api.dto.rule.PluginGroup;
import cn.young.im.plugin.base.AbstractSimpleYoungPlugin;
import lombok.extern.slf4j.Slf4j;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 用户相关操作处理插件
 * @date 2023/11/26
 */
@Slf4j
public class PrivilegeProcessPlugin extends AbstractSimpleYoungPlugin {
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public String getIdentity() {
        return "权益校验插件";
    }

    @Override
    public PluginGroup acquireGroup() {
        return PluginGroup.ESSENTIAL_STRATEGY;
    }

    @Override
    public boolean isAvailable(YoungPluginContext context) {
        return true;
    }

    @Override
    public YoungPluginResult doExecute(YoungPluginContext context) {
        // todo 真的鉴权
        PrivilegePluginResult privilegePluginResult = new PrivilegePluginResult();
        privilegePluginResult.setTips(YoungPluginConst.PRIVILEGE_NOT_PERMITTED);
        privilegePluginResult.setAppId(context.getMessage().getMessageHeader().getAppId());
        // 中断下面的插件执行
        context.setInterrupt(true);
        log.info("appId租户{}暂未获得{}插件链的权益", context.getMessage().getMessageHeader().getAppId(), context.getPluginChainName());
        return privilegePluginResult;
    }
}