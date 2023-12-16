package cn.young.im.springboot.starter.extension.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 感知 Application Context 全局使用
 * @date 2023/12/16
 */
public class ApplicationContextHolder implements ApplicationContextAware {

    /**
     * Application Context
     */
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }
}
