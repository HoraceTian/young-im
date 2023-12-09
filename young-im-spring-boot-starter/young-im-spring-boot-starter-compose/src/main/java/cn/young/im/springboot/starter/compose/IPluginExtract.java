package cn.young.im.springboot.starter.compose;

import cn.young.im.plugin.api.YoungPlugin;
import cn.young.im.plugin.api.dto.rule.PluginGroup;

import java.util.List;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 插件提取
 * @date 2023/11/26
 */
public interface IPluginExtract {

    /**
     * 根据插件组提取插件
     */
    List<YoungPlugin> extractPluginByGroup(PluginGroup group);
}
