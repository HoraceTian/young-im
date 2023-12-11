package cn.young.im.gateway;

import cn.young.im.config.api.ConfigCenterClientFactory;
import cn.young.im.springboot.starter.adapter.registry.InstanceRegistryService;
import cn.young.im.springboot.starter.adapter.registry.adapter.NacosInstanceRegistryService;
import cn.young.im.springboot.starter.adapter.registry.annotation.AutomaticRegistry;
import cn.young.im.springboot.starter.adapter.registry.config.NacosConfig;
import cn.young.im.springboot.starter.adapter.registry.config.RegisterConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

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
@AutomaticRegistry
public class GatewayApplication implements CommandLineRunner {

    @Resource
    private ConfigCenterClientFactory centerClientFactory;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GatewayApplication.class);

        NacosConfig nacos = new NacosConfig();
        nacos.setNamespace("538350b3-5628-440e-b221-0fffcc6cdf55");
        nacos.setUsername("nacos");
        nacos.setPassword("thr010410");
        nacos.setGroup("im");

        RegisterConfig config = new RegisterConfig();
        config.setMode("AP");
        config.setRegistryType("nacos");
        config.setServerLists("124.222.135.79:8848");
        config.setNacos(nacos);

        InstanceRegistryService instanceRegistryService = context.getBean(InstanceRegistryService.class);

        instanceRegistryService.init(config);
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
