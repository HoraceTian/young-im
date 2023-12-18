package cn.young.im.springboot.starter.adapter.config.bind;

import cn.young.im.common.exception.YoungImException;
import cn.young.im.springboot.starter.adapter.config.callback.ConfigRefreshCallBack;
import cn.young.im.springboot.starter.adapter.config.repository.ConfigServerRepository;
import cn.young.im.springboot.starter.adapter.config.ConfigType;
import cn.young.im.springboot.starter.adapter.config.anno.ConfigServerConfigurationProperties;
import cn.young.im.springboot.starter.adapter.config.parse.ConfigParseFactory;
import cn.young.im.springboot.starter.adapter.config.parse.IConfigParseHandler;
import cn.young.im.springboot.starter.extension.spring.ApplicationContextHolder;
import com.alibaba.nacos.api.config.listener.AbstractListener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Objects;

@AllArgsConstructor
public class ConfigServerConfigurationBind implements BeanPostProcessor {

    private final ConfigServerRepository configServerRepository;

    private final ApplicationContextHolder contextHolder;

    /**
     * 实例化前对注解进行处理
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, @NonNull String beanName) throws BeansException {
        // 1. 提取注解
        ConfigServerConfigurationProperties annotation =
                AnnotationUtils.findAnnotation(bean.getClass(), ConfigServerConfigurationProperties.class);

        // 2. 排除非配置 Bean
        if (Objects.isNull(annotation)) {
            return bean;
        }

        // 3. 存在注解开始绑定
        bind(bean, beanName, annotation);

        return bean;
    }

    /**
     * 属性与系统绑定
     */

    private void bind(final Object bean, final String beanName,
                      final ConfigServerConfigurationProperties annotation) {

        // 1. 提取属性
        final String dataId = annotation.configKey();
        final String groupId = annotation.groupId();
        final String type = deduceConfigType(annotation);
        final ConfigRefreshCallBack callBack =
                (ConfigRefreshCallBack) contextHolder.getContext().getBean(annotation.callbackClazz());

        // 2. 解析属性
        String content = configServerRepository.getConfig(dataId, groupId);

        // 把配置文件塞进属性 (handler) + callback
        IConfigParseHandler handler = ConfigParseFactory.acquireHandler(type);
        handler.parse(content, bean);

        // 3. 自动刷新监听器注册
        if (annotation.autoRefreshed()) {
            try {
                configServerRepository.getConfigService().addListener(dataId, groupId, new AbstractListener() {
                    final String _beanName = beanName;
                    final ApplicationContextHolder _contextHolder = contextHolder;
                    final ConfigRefreshCallBack _callBack = callBack;
                    final String _type = type;

                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        // 1. 提取配置 Bean
                        Object originBean = _contextHolder.getContext().getBean(_beanName);

                        // 2. 提取处理器
                        IConfigParseHandler handler = ConfigParseFactory.acquireHandler(_type);

                        // 3. 执行处理
                        handler.parse(configInfo, originBean);

                        // 4. 触发回调
                        _callBack.refresh();
                    }
                });
            } catch (NacosException e) {
                throw new YoungImException("listener init occur error, config bean is :" + beanName);
            }
        }
    }

    /**
     * 推断 Config Type
     */
    private String deduceConfigType(final ConfigServerConfigurationProperties annotation) {
        String type;
        ConfigType configType = annotation.type();

        // 处理未设情况 (尝)
        if (ConfigType.UNSET.equals(configType)) {
            String dataId = annotation.configKey();
            // 没有设置就取ConfigKey的后缀名，没有直接报错
            int dotIndex = dataId.lastIndexOf(".");
            if (dotIndex == -1) {
                throw new YoungImException("please set config type with @" + ConfigServerConfigurationProperties.class.getName());
            }
            // 判断 dataId 截取后缀的合法性
            String subjectType = dataId.substring(dotIndex + 1);
            type = ConfigType.isValidType(subjectType) ? subjectType : ConfigType.getDefaultType().getType();
        } else {
            type = configType.getType();
        }
        return type;
    }
}
