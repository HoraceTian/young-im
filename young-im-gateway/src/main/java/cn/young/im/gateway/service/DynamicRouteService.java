package cn.young.im.gateway.service;

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
public class DynamicRouteService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * 发布配置刷新事件 - Spring Cloud Gateway 内部消费
     */
    public void refreshRoute() {
        publisher.publishEvent(new RefreshRoutesEvent(this));
    }
}
