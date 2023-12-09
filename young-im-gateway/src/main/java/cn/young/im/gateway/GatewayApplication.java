package cn.young.im.gateway;

import cn.young.im.config.api.ConfigCenterClientFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description Gateway 网关
 * @date 2023/11/26
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication implements CommandLineRunner {

    @Resource
    private ConfigCenterClientFactory centerClientFactory;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        String config = centerClientFactory.getInstance().getConfig("pluginChain", "DEFAULT_GROUP");
        System.out.println(config);
        config = centerClientFactory.getInstance().getConfig("pluginChain", "DEFAULT_GROUP");
        System.out.println(config);
        TimeUnit.SECONDS.sleep(1);
        String config1 = centerClientFactory.getInstance().getConfig("pluginChain", "DEFAULT_GROUP");
        System.out.println(config1);
    }
}
