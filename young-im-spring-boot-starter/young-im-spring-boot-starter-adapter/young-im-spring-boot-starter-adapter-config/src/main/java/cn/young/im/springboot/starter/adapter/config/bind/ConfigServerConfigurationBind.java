package cn.young.im.springboot.starter.adapter.config.bind;

import cn.young.im.springboot.starter.adapter.config.ConfigPropertyResolver;
import cn.young.im.springboot.starter.adapter.config.ConfigServerConfigurationProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
public class ConfigServerConfigurationBind implements BeanPostProcessor {

    private final ConfigPropertyResolver configPropertyResolver;

    /**
     * 实例化前对注解进行处理
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
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


    }
}
