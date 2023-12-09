package cn.young.im.config.api.adapter;

import cn.young.im.config.api.AbstractConfigCenterClient;
import cn.young.im.config.api.ConfigCenterClient;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Properties;

/**
 * @ClassName NacosConfigCenterClient
 * @Description
 * @date 2023/12/7 15:19
 * @Author yanceysong
 * @Version 1.0
 */
@Component
@Slf4j
@AllArgsConstructor
public class NacosConfigCenterClient extends AbstractConfigCenterClient {

    private ConfigService configService;

    @Override
    public String getConfig(String configKey, String groupName) {

        try {
            return configService.getConfig(configKey, groupName, 5000);
        } catch (NacosException e) {
            log.error(e.getErrMsg(), e);
        }
        return null;
    }

    @Override
    public void refreshConfigCache(String configKey, String newValue) {

    }
}
