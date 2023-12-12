package cn.young.im.spi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpiConfiguration {

    @Bean
    public SpiExtensionFactory spiExtensionFactory(){
        return new SpiExtensionFactory();
    }
}
