package cn.young.im.config.api;

import cn.young.im.config.api.adapter.NacosConfigCenterClient;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public NacosConfigCenterClient nacosConfigCenterClient(ApplicationContext applicationContext) throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", System.getenv("NACOS_CONFIG_SERVER"));
        properties.put("namespace", System.getenv("NACOS_NAMESPACE"));
        ConfigService configService = NacosFactory.createConfigService(properties);
        return new NacosConfigCenterClient(configService, applicationContext);
    }
}
