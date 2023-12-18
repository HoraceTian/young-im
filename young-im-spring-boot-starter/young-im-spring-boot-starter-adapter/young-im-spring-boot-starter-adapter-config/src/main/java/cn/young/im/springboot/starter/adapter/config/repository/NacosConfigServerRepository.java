package cn.young.im.springboot.starter.adapter.config.repository;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description Nacos 配置中心
 * @date 2023/12/16
 */
@Slf4j
@AllArgsConstructor
public class NacosConfigServerRepository implements ConfigServerRepository {

    private final ConfigService configService;

    @Override
    public String getConfig(String configKey, String groupId) {
        try {
            return configService.getConfig(configKey, groupId, 5000);
        } catch (NacosException e) {
            log.error(e.getErrMsg(), e);
        }
        return null;
    }

    @Override
    public ConfigService getConfigService() {
        return configService;
    }
}
