package cn.young.im.gateway.config;

import cn.young.im.springboot.starter.adapter.config.ConfigServerConfigurationProperties;
import cn.young.im.springboot.starter.adapter.config.ConfigType;
import lombok.Data;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 路由配置接收处
 * @date 2023/12/16
 */
@Data
@Component
@ConfigServerConfigurationProperties
        (configKey = "route", groupId = "young_im", autoRefreshed = true, type = ConfigType.JSON)
public class RouteProperties {
    private List<RouteDefinition> route;
}
