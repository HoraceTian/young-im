package cn.young.im.springboot.starter.compose;

import cn.young.im.plugin.api.YoungPlugin;
import cn.young.im.plugin.api.dto.rule.PluginGroup;

import java.util.List;
import java.util.Map;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 插件管家
 * @date 2023/11/26
 */
public class PluginManager implements IPluginExtract {

    private final Map<PluginGroup, List<YoungPlugin>> plugins;

    public PluginManager(Map<PluginGroup, List<YoungPlugin>> plugins) {
        this.plugins = plugins;
    }

    @Override
    public List<YoungPlugin> extractPluginByGroup(PluginGroup group) {
        return plugins.get(group);
    }
}
