package cn.young.im.springboot.starter.adapter.registry.annotation;

import cn.young.im.common.constants.YoungConst;

import java.lang.annotation.*;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 自动注册注解
 * @date 2023/12/9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface AutomaticRegistry {

    /**
     * 服务名
     */
    String serviceName() default YoungConst.DEFAULT;

    /**
     * 主机地址
     */
    String host() default YoungConst.DEFAULT;

    /**
     * 端口
     */
    String port() default YoungConst.DEFAULT;

    /**
     * 权重
     */
    double weight() default 1.0D;
}
