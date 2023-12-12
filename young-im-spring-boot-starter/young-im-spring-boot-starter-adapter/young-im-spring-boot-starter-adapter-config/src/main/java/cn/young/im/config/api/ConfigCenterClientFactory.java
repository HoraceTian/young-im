package cn.young.im.config.api;

import cn.young.im.config.api.adapter.ApolloConfigCenterClient;
import cn.young.im.config.api.adapter.NacosConfigCenterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName ConfigCenterClientFactory
 * @Description
 * @date 2023/12/7 15:26
 * @Author yanceysong
 * @Version 1.0
 */
@Service
public class ConfigCenterClientFactory {
    private volatile static ConfigCenterClient INSTANCE;
    @Autowired
    private NacosConfigCenterClient nacosConfigCenterClient;

    /**
     * 获取配置中心客户端的单例
     *
     * @return 配置中心客户端的实现
     */
    public ConfigCenterClient getInstance() {
        if (INSTANCE == null) {
            synchronized (ConfigCenterClient.class) {
                if (INSTANCE == null) {
                    // 在这里去选址使用哪个配置中心
                    INSTANCE = nacosConfigCenterClient;
                }
            }
        }
        return ConfigCenterClientFactory.INSTANCE;
    }
}
