package cn.young.im.gateway.config;

import cn.young.im.gateway.service.DynamicRouteService;
import cn.young.im.springboot.starter.adapter.config.ConfigType;
import cn.young.im.springboot.starter.adapter.config.anno.ConfigServerConfigurationProperties;
import lombok.Data;
import org.springframework.cloud.gateway.route.RouteDefinition;
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
        (configKey = "router", groupId = "young_im", autoRefreshed = true, type = ConfigType.JSON,
                callbackClazz = DynamicRouteService.class)
public class RouteProperties {
    private List<RouteDefinition> route;
}
