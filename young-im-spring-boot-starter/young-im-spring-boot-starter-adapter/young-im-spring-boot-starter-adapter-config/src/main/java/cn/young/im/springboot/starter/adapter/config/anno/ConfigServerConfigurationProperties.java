package cn.young.im.springboot.starter.adapter.config.anno;

import cn.young.im.springboot.starter.adapter.config.ConfigType;
import cn.young.im.springboot.starter.adapter.config.callback.EmptyConfigRefreshCallBack;

import java.lang.annotation.*;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 配置中心配置读取注解
 * @date 2023/12/16
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ConfigServerConfigurationProperties {

    /**
     * 配置前缀
     */
    String prefix() default "";

    /**
     * 如果是Nacos 那么则是 dataId
     * 如果是Apollo 则是ConfigKeyName
     */
    String configKey();

    /**
     * 如果是Nacos 那么则是 groupId
     * 如果是Apollo 则是namespace
     */
    String groupId() default "";

    /**
     * 配置后缀
     */
    ConfigType type() default ConfigType.UNSET;

    /**
     * 是否自动刷新
     */
    boolean autoRefreshed() default true;

    /**
     * 回调类型
     */
    Class<?> callbackClazz() default EmptyConfigRefreshCallBack.class;
}
