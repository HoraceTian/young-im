package cn.young.im.springboot.starter.adapter.registry;

import cn.young.im.springboot.starter.adapter.registry.config.RegisterConfig;

import java.util.List;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description
 * @date 2023/12/9
 */
public interface InstanceRegistryService {

    /**
     * 初始化注册中心
     */
    default void init(RegisterConfig config) {

    }

    /**
     * 注册
     */
    void registry(Instance instance);


    /**
     * 获取实例
     */
    List<Instance> listOfInstance(String tenant);
}
