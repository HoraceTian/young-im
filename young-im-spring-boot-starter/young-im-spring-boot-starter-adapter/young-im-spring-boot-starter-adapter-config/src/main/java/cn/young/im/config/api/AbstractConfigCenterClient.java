package cn.young.im.config.api;

import cn.young.im.config.api.anno.RefreshConfig;
import cn.young.im.util.AnnotationScanner;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @ClassName AbstractConfigCenterTarget
 * @Description
 * @date 2023/12/7 15:03
 * @Author yanceysong
 * @Version 1.0
 */
@Slf4j
public abstract class AbstractConfigCenterClient implements ConfigCenterClient {
    protected final Map<Class<?>, Annotation> refreshConfigEntity;

    public AbstractConfigCenterClient() {
        this.refreshConfigEntity = AnnotationScanner.scanClassByAnnotation("cn.young.im", RefreshConfig.class);
    }

    /**
     * 不同的配置中心适配器去实现该方法，对特定的配置进行配置变更监听并更新
     */
    protected abstract void initListener(ApplicationContext applicationContext);

    /**
     * 初始化配置类与配置中心的映射
     */
    protected abstract void configMapping(ApplicationContext applicationContext);

    /**
     * 更新本地实体类的配置
     *
     * @param configBean 配置类
     * @param newValue   新的值
     */
    protected void refreshConfig(Object configBean, String newValue) {
        // 解析成为JSONObject
        JSONObject newConfigJson = JSONObject.parseObject(newValue);
        // 新的配置型重新解析
        Object newConfigBean = newConfigJson.toJavaObject(configBean.getClass());
        // 对原来的对象进行赋值
        BeanUtils.copyProperties(newConfigBean, configBean);
    }
}
