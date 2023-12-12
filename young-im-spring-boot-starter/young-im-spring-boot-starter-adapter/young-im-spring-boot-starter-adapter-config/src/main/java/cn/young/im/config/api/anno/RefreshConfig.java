package cn.young.im.config.api.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RefreshConfig {
    /**
     * 如果是Nacos 那么则是 dataId
     * 如果是Apollo 则是ConfigKeyName
     */
    String dataId();

    /**
     * 如果是Nacos 那么则是 groupId
     * 如果是Apollo 则是namespace
     */
    String groupId();
}
