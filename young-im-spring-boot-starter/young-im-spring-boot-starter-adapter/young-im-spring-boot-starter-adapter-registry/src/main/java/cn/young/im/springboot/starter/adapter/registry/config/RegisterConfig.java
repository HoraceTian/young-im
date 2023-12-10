package cn.young.im.springboot.starter.adapter.registry.config;

import lombok.Data;

import java.util.Properties;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 注册配置
 * @date 2023/12/9
 */
@Data
public class RegisterConfig {

    /**
     * 实例刷新模式
     */
    private String mode;

    /**
     * 注册类型
     */
    private String registryType;

    /**
     * 注册中心列表
     */
    private String serverLists;

    /**
     * Nacos
     */
    private NacosConfig nacos;
}
