package cn.young.im.gateway.repository;

import cn.young.im.common.exception.YoungImException;
import cn.young.im.gateway.config.RouteProperties;
import cn.young.im.springboot.starter.adapter.config.repository.ConfigServerRepository;
import com.alibaba.fastjson2.JSON;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.synchronizedMap;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 内部路由定义初始化
 * @date 2023/12/4
 */
@Component
public class ConfigServerRouteDefinitionRepository implements RouteDefinitionRepository {
    /**
     * 路由库
     */
    public static final Map<String, RouteDefinition> routes = synchronizedMap(new LinkedHashMap<>());

//    /**
//     * 配置仓储
//     */
//    @Resource
//    private ConfigServerRepository configServerRepository;

//    /**
//     * 初始化路由
//     */
//    @PostConstruct
//    public void initRoutes() {
//        String configJson = configServerRepository.getConfig("router", "young_im");
//        RouteProperties routeProperties = JSON.parseObject(configJson, RouteProperties.class);
//        for (RouteDefinition route : routeProperties.getRoute()) {
//            routes.put(route.getId(), route);
//        }
//    }

    /**
     * 获取路由
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routes.values());
    }

    /**
     * 保存路由
     */
    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(r -> {
            routes.put(r.getId(), r);
            return Mono.empty();
        });
    }

    /**
     * 删除路由
     */
    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            if (routes.containsKey(id)) {
                routes.remove(id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new YoungImException("RouteDefinition not found: " + routeId)));
        });
    }
}
