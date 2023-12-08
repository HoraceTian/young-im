package cn.young.im.config.api;

import cn.young.im.config.api.adapter.NacosConfigCenterClient;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Properties;

/**
 * @ClassName ConfigCenterClientFactoryConfiguration
 * @Description
 * @date 2023/12/7 15:39
 * @Author yanceysong
 * @Version 1.0
 */
@Configuration
public class ConfigCenterClientFactoryConfiguration {

    @Bean
    public ConfigCenterClientFactory configCenterClientFactory() {
        return new ConfigCenterClientFactory();
    }

    @Bean
    public NacosConfigCenterClient nacosConfigCenterClient(ConfigurableEnvironment environment) throws NacosException {
        ConfigService configService = NacosFactory.createConfigService(System.getenv("NACOS_CONFIG_SERVER"));
        return new NacosConfigCenterClient(configService);
    }
}
