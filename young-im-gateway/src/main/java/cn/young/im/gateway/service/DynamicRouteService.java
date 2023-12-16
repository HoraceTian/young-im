package cn.young.im.gateway.service;

import cn.young.im.springboot.starter.adapter.config.ConfigRefreshCallBack;
import lombok.NonNull;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 动态路由事件发布
 * @date 2023/12/4
 */
@Component
public class DynamicRouteService implements
        ApplicationEventPublisherAware, ConfigRefreshCallBack {

    /**
     * 事件发布器
     */
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * 发布配置刷新事件 - Spring Cloud Gateway 内部消费
     */
    public void refreshRoute() {
        publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * 配置刷新回调
     */
    @Override
    public void refresh() {
        refreshRoute();
    }
}
