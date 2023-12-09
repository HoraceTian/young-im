package cn.young.im.springboot.starter.adapter.registry;

import lombok.Data;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description
 * @date 2023/12/9
 */
@Data
public class Instance {

    /**
     * 主机地址
     */
    private String host;

    /**
     * 端口号
     */
    private String port;

    /**
     * 实例健康状态
     */
    private boolean healthy;

    /**
     * 权重
     */
    private double weight;

    /**
     * 客户端名称
     */
    private String clientName;
}
