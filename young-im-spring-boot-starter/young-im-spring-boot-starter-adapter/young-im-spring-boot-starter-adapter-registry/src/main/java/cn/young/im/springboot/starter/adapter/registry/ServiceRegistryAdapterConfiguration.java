package cn.young.im.springboot.starter.adapter.registry;

import cn.young.im.springboot.starter.adapter.registry.adapter.NacosInstanceRegistryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description
 * @date 2023/12/9
 */
@Configuration
public class ServiceRegistryAdapterConfiguration {

    @Bean
    public InstanceRegistryService instanceRegistryService(){
        return new NacosInstanceRegistryService();
    }
}
