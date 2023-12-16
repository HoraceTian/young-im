package cn.young.im.springboot.starter.adapter.config;

import cn.young.im.common.exception.YoungImException;
import cn.young.im.spi.ExtensionFactory;
import cn.young.im.springboot.starter.adapter.config.bind.ConfigServerConfigurationBind;
import cn.young.im.springboot.starter.adapter.config.repository.NacosConfigServerRepository;
import cn.young.im.springboot.starter.extension.spring.ApplicationContextHolder;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.Properties;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 配置中心适配层自动装配
 * @date 2023/12/16
 */
@Slf4j
@Configuration
public class ConfigServerAdapterConfiguration {


    /**
     * 配置属性解析器
     */
    @Bean
    public ConfigPropertyResolver configPropertyResolver(
            @Value("${young.im.config.configServerType}") String configServerType,
            ObjectProvider<ExtensionFactory> extensionFactoryProvider) {

        // 1. 获取 SPI 工厂
        ExtensionFactory extensionFactory = extensionFactoryProvider.getIfAvailable();

        // 2. 判断 SPI 工厂
        if (Objects.isNull(extensionFactory)) {
            throw new YoungImException("inject SpiExtensionFactory occur error");
        }

        // 4. 选择 Resolver 类型
        return extensionFactory.getExtension(configServerType, ConfigPropertyResolver.class);
    }

    /**
     * 配置中心仓储
     */
    @Bean
    public ConfigServerRepository configServerRepository(
            @Value("${young.im.config.configServerType}") String configServerType) {
        ConfigServerRepository repository = null;
        switch (configServerType) {
            case "nacos":
                repository = new NacosConfigServerRepository(createNacosConfigService());
                break;
            case "apollo":
                break;
        }
        return repository;
    }

    /**
     * 配置绑定器
     */
    @Bean
    public ConfigServerConfigurationBind configServerConfigurationBind(
            ConfigPropertyResolver configPropertyResolver,
            ObjectProvider<ConfigServerRepository> repositoryProvider, ApplicationContextHolder contextHolder) {
        return new ConfigServerConfigurationBind(configPropertyResolver, repositoryProvider.getIfAvailable(), contextHolder);
    }

    /**
     * 创建 Nacos Config Service
     */
    private ConfigService createNacosConfigService() {
        ConfigService configService = null;
        try {
            Properties properties = new Properties();
            properties.put("serverAddr", System.getenv("NACOS_CONFIG_SERVER"));
            properties.put("namespace", System.getenv("NACOS_NAMESPACE"));
            properties.put("username", System.getenv("NACOS_USERNAME"));
            properties.put("password", System.getenv("NACOS_PASSWORD"));
            configService = NacosFactory.createConfigService(properties);
        } catch (NacosException e) {
            log.error("create nacos config service occur error");
            throw new YoungImException(e);
        }
        return configService;
    }
}
