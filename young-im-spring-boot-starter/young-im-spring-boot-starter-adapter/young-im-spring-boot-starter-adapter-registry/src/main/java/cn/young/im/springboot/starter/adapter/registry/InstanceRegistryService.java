package cn.young.im.springboot.starter.adapter.registry;

import cn.young.im.spi.SPI;
import cn.young.im.springboot.starter.adapter.registry.config.RegisterConfig;

import java.util.List;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description
 * @date 2023/12/9
 */
@SPI("nacos")
public interface InstanceRegistryService {

    /**
     * 初始化注册中心
     */
    default void init(RegisterConfig config) {

    }

    /**
     * 注册
     */
    void registry(InstanceEntity instanceEntity);


    /**
     * 获取实例
     */
    List<InstanceEntity> listOfInstance(String tenant);
}
