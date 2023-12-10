package cn.young.im.springboot.starter.adapter.registry.config;

import lombok.Data;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description Nacos
 * @date 2023/12/10
 */
@Data
public class NacosConfig {

    /**
     * 租户号
     */
    private String namespace;

    /**
     * 组
     */
    private String group;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
