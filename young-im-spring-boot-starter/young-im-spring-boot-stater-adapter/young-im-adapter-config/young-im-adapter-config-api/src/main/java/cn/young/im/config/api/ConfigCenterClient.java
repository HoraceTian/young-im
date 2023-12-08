package cn.young.im.config.api;

/**
 * @ClassName ConfigCenterTarget
 * @Description
 * @date 2023/12/7 14:40
 * @Author yanceysong
 * @Version 1.0
 */
public interface ConfigCenterClient {
    /**
     * 从配置中心获取配置
     *
     * @param configKey 配置项名字
     * @param groupName 分组，在nacos是group，在Apollo是Cluster
     * @return 配置内容
     */
    String getConfig(String configKey, String groupName);

    /**
     * 更新本地配置缓存
     *
     * @param configKey 配置项名字
     */
    void refreshConfigCache(String configKey,String newValue);
}
