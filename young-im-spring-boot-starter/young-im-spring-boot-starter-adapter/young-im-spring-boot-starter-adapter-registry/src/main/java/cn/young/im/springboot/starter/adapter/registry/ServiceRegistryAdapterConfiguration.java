package cn.young.im.springboot.starter.adapter.registry;

import cn.young.im.spi.ExtensionFactory;
import cn.young.im.spi.ExtensionLoader;
import cn.young.im.spi.SpiExtensionFactory;
import cn.young.im.springboot.starter.adapter.registry.config.RegisterConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description
 * @date 2023/12/9
 */
@Configuration
@EnableConfigurationProperties(RegisterConfig.class)
public class ServiceRegistryAdapterConfiguration {

    /**
     * 实例注册服务
     */
    @Bean
    public InstanceRegistryService instanceRegistryService(RegisterConfig registerConfig, ExtensionFactory extensionFactory) {
        InstanceRegistryService instanceRegistryService =
                extensionFactory.getExtension(registerConfig.getRegistryType(), InstanceRegistryService.class);
        instanceRegistryService.init(registerConfig);
        return instanceRegistryService;
    }
}
