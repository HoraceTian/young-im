package cn.young.im.springboot.starter.extension;

import cn.young.im.spi.ExtensionFactory;
import cn.young.im.spi.ExtensionLoader;
import cn.young.im.spi.SpiExtensionFactory;
import cn.young.im.springboot.starter.extension.spring.ApplicationContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 扩展点自动装配
 * @date 2023/12/16
 */
@Configuration
public class ExtensionAutoConfiguration {
    /**
     * SPI 工厂注入
     */
    @Bean
    public ExtensionFactory extensionFactory(){
        return ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getDefaultJoin();
    }

    /**
     * ApplicationContextHolder
     */
    @Bean
    public ApplicationContextHolder contextHolder(){
        return new ApplicationContextHolder();
    }
}
