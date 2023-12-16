package cn.young.im.gateway;

import cn.young.im.gateway.config.RouteProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description Gateway 网关
 * @date 2023/11/26
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GatewayApplication.class);
        RouteProperties bean = context.getBean(RouteProperties.class);
        System.out.println("bean = " + bean);
    }
}
