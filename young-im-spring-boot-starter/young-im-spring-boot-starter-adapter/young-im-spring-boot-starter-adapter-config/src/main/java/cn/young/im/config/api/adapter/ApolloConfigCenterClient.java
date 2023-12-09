package cn.young.im.config.api.adapter;

import cn.young.im.config.api.AbstractConfigCenterClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName ApolloConfigCenterClient
 * @Description
 * @date 2023/12/7 15:20
 * @Author yanceysong
 * @Version 1.0
 */
@Service
@Slf4j
public class ApolloConfigCenterClient extends AbstractConfigCenterClient {
    @Override
    public String getConfig(String configKey, String groupName) {
        return configCache.get(configKey);
    }

    @Override
    public void refreshConfigCache(String configKey, String newValue) {
        configCache.put(configKey, newValue);
    }
}
