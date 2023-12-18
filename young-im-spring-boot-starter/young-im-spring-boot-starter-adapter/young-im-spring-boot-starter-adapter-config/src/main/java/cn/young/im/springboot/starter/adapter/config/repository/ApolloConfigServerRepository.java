package cn.young.im.springboot.starter.adapter.config.repository;

import com.alibaba.nacos.api.config.ConfigService;

/**
 * @ClassName ApolloConfigServerRepository
 * @Description
 * @date 2023/12/18 10:07
 * @Author yanceysong
 * @Version 1.0
 */
public class ApolloConfigServerRepository implements ConfigServerRepository {
    @Override
    public String getConfig(String configKey, String groupId) {
        return null;
    }

    @Override
    public ConfigService getConfigService() {
        return null;
    }
}
