package cn.young.im.config.api.adapter;

import cn.young.im.config.api.AbstractConfigCenterClient;
import cn.young.im.config.api.anno.RefreshConfig;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;

/**
 * @ClassName NacosConfigCenterClient
 * @Description
 * @date 2023/12/7 15:19
 * @Author yanceysong
 * @Version 1.0
 */
@Component
@Slf4j
public class NacosConfigCenterClient extends AbstractConfigCenterClient {

    private final ConfigService configService;

    private ApplicationContext applicationContext;

    public NacosConfigCenterClient(ConfigService configService, ApplicationContext applicationContext) {
        super();
        this.configService = configService;
        this.applicationContext = applicationContext;
        initListener(applicationContext);
        configMapping(applicationContext);
    }


    @Override
    public String getConfig(String configKey, String groupName) {
        try {
            return configService.getConfig(configKey, groupName, 5000);
        } catch (NacosException e) {
            log.error(e.getErrMsg(), e);
        }
        return null;
    }

    @Override
    protected void initListener(ApplicationContext applicationContext) {
        try {
            for (Map.Entry<Class<?>, Annotation> refreshConfigEntry : super.refreshConfigEntity.entrySet()) {
                RefreshConfig configOptionInfo = (RefreshConfig) refreshConfigEntry.getValue();
//                configService.addListener(configOptionInfo.dataId(), configOptionInfo.groupId(), new AbstractConfigChangeListener() {
//                    @Override
//                    public void receiveConfigChange(ConfigChangeEvent configChangeEvent) {
//                        System.out.println(configChangeEvent.toString());
//                        for (ConfigChangeItem changeItem : configChangeEvent.getChangeItems()) {
//                            log.debug(changeItem.toString());
//                            Optional<? extends Class<?>> updateConfigClass = getClassByAnnotationInfo(configOptionInfo.dataId(), configOptionInfo.groupId());
//                            if (updateConfigClass.isPresent() && PropertyChangeType.MODIFIED.equals(changeItem.getType())) {
//                                // 获取到这个配置在spring中的对象
//                                Object configBean = applicationContext.getBean(updateConfigClass.get());
//                                // 拿到更新后的新的值
//                                String newValue = changeItem.getNewValue();
//                                refreshConfig(configBean, newValue);
//                            }
//                        }
//                    }
//                });
                configService.addListener(configOptionInfo.dataId(), configOptionInfo.groupId(), new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }

                    @Override
                    public void receiveConfigInfo(String newValue) {
                        log.debug("本地收到收到nacos变更配置内容:{}", newValue);
                        Optional<? extends Class<?>> updateConfigClass = getClassByAnnotationInfo(configOptionInfo.dataId(), configOptionInfo.groupId());
                        if (updateConfigClass.isPresent()) {
                            // 获取到这个配置在spring中的对象
                            Object configBean = applicationContext.getBean(updateConfigClass.get());
                            refreshConfig(configBean, newValue);
                        }
                    }
                });
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    protected void configMapping(ApplicationContext applicationContext) {
        for (Map.Entry<Class<?>, Annotation> refreshConfigEntry : super.refreshConfigEntity.entrySet()) {
            Object configBean = applicationContext.getBean(refreshConfigEntry.getKey());
            RefreshConfig configOptionInfo = (RefreshConfig) refreshConfigEntry.getValue();
            try {
                String config = configService.getConfig(configOptionInfo.dataId(), configOptionInfo.groupId(), 3000L);
                refreshConfig(configBean, config);
            } catch (NacosException e) {
                log.error(e.getErrMsg(), e);
            }
        }
    }

    /**
     * 根据dataId和groupId找到需要更新的配置类的class对象
     *
     * @param dataId  dataId
     * @param groupId groupId
     * @return 配置类的class对象
     */
    private Optional<? extends Class<?>> getClassByAnnotationInfo(String dataId, String groupId) {
        return refreshConfigEntity.entrySet()
                .stream()
                .filter(entry -> {
                    RefreshConfig configOptionInfo = (RefreshConfig) entry.getValue();
                    return configOptionInfo.dataId().equals(dataId) && configOptionInfo.groupId().equals(groupId);
                })
                .map(Map.Entry::getKey)
                .findFirst();
    }
}
