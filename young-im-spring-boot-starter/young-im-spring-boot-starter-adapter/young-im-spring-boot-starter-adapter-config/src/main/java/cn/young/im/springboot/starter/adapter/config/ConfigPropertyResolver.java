package cn.young.im.springboot.starter.adapter.config;

import cn.young.im.spi.SPI;
import org.springframework.beans.PropertyValues;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 配置属性解析器
 * @date 2023/12/16
 */
@SPI("nacos")
public interface ConfigPropertyResolver {

    /**
     * 解析属性
     */
    PropertyValues resolvePropertyValues(Object bean, String prefix,
                                         String configKey, String groupId, String type, String configContext);

}
