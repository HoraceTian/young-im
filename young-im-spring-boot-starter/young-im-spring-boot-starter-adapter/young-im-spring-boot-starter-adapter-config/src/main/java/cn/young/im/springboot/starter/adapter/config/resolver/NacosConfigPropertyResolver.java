package cn.young.im.springboot.starter.adapter.config.resolver;

import cn.young.im.spi.Join;
import cn.young.im.springboot.starter.adapter.config.ConfigPropertyResolver;
import org.springframework.beans.PropertyValues;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description Nacos 配置解析
 * @date 2023/12/16
 */
@Join
public class NacosConfigPropertyResolver implements ConfigPropertyResolver {
    @Override
    public PropertyValues resolvePropertyValues(Object bean, String prefix,
                                                String configKey, String groupId, String type, String configContext) {
        return null;
    }
}
