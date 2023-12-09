package cn.young.im.springboot.starter.adapter.registry;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 服务注册常量
 * @date 2023/12/9
 */
public class ServiceRegistryConstants {

    /**
     * 刷新模式 - 高可用
     */
    public static final String AP = "ap";

    /**
     * 刷新模式 - 强一致
     */
    public static final String CP = "cp";


    /**
     * 注册中心 Nacos
     */
    public static final String NACOS = "nacos";

    /**
     * 注册中心 Apollo
     */
    public static final String APOLLO = "apollo";

    /**
     * 注册中心 Zookeeper
     */
    public static final String ZK = "zookeeper";
}
