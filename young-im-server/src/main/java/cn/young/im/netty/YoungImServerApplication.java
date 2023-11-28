package cn.young.im.netty;

import cn.young.im.netty.server.ImWebSocketServer;
import cn.young.im.netty.config.ImNettyConfig;
import cn.young.im.plugin.api.YoungPlugin;
import cn.young.im.plugin.api.dto.rule.PluginGroup;
import cn.young.im.springboot.starter.compose.PluginManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
public class YoungImServerApplication implements CommandLineRunner {
    @Resource
    private ImNettyConfig imNettyConfig;

    @Resource
    private PluginManager pluginManager;

    public static void main(String[] args) {
        SpringApplication.run(YoungImServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        new ImWebSocketServer(imNettyConfig).start();
        List<YoungPlugin> youngPlugins = pluginManager.extractPluginByGroup(PluginGroup.MESSAGE_STRATEGY);

        for (YoungPlugin plugin : youngPlugins) {
            plugin.execute(null);
        }
    }
}
