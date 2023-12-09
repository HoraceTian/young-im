package cn.young.im.config.api;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.Weighers;

import java.util.Map;

/**
 * @ClassName AbstractConfigCenterTarget
 * @Description
 * @date 2023/12/7 15:03
 * @Author yanceysong
 * @Version 1.0
 */
public abstract class AbstractConfigCenterClient implements ConfigCenterClient {
    protected Map<String, String> configCache;

    public AbstractConfigCenterClient() {
        int cacheSize = 128;
        configCache = new ConcurrentLinkedHashMap.Builder<String, String>()
                .maximumWeightedCapacity(cacheSize)
                .weigher(Weighers.singleton())
                .build();
    }
}
