package cn.young.im.springboot.starter.plugin.user;

import cn.young.im.plugin.api.YoungPlugin;
import cn.young.im.plugin.user.UserProcessPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 用户处理插件自动配置
 * @date 2023/11/26
 */
@Configuration
public class UserProcessPluginConfiguration {

    /**
     * 用户处理插件
     */
    @Bean
    public YoungPlugin userProcessPlugin() {
        return new UserProcessPlugin();
    }
}
