package cn.young.im.springboot.starter.compose.autoconfigure;

import cn.young.im.plugin.api.YoungPlugin;
import cn.young.im.plugin.api.dto.rule.PluginGroup;
import cn.young.im.springboot.starter.compose.PluginManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 插件自动加载配置
 * @date 2023/11/26
 */
@Configuration
public class PluginComposeConfiguration {
    /**
     * 插件管家
     */
    @Bean
    public PluginManager pluginManager(final ObjectProvider<List<YoungPlugin>> plugins) {

        Map<PluginGroup, List<YoungPlugin>> pluginMap =
                Objects.requireNonNull(plugins.getIfAvailable())
                        .stream()
                        .collect(Collectors.groupingBy(YoungPlugin::acquireGroup));

        return new PluginManager(pluginMap);
    }
}
