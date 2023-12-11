package cn.young.im.springboot.starter.adapter.registry;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description
 * @date 2023/12/9
 */
@Data
@Builder
@Accessors(chain = true)
public class InstanceEntity {

    /**
     * 主机地址
     */
    private String host;

    /**
     * 端口号
     */
    private int port;

    /**
     * 实例健康状态
     */
    private Status status;

    /**
     * 权重
     */
    private double weight = 1.0D;

    /**
     * 客户端名称
     */
    private String serviceName;
}
