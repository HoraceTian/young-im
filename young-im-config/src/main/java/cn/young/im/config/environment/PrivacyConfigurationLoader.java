package cn.young.im.config.environment;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 处理隐私配置
 * @date 2023/11/12
 */
@Component
public class PrivacyConfigurationLoader implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // 1. 提取配置
        String configPath = System.getenv("CONFIG_PATH");
        String config = FileUtil.readString(new File(configPath), Charset.defaultCharset());

        // 2. 解析源文件
        JSONObject jsonObj = JSON.parseObject(config);

        // 3. 覆盖配置
        environment.getSystemProperties().put("spring.datasource.url", jsonObj.getString("mysql-url"));
        environment.getSystemProperties().put("spring.datasource.username", jsonObj.getString("mysql-username"));
        environment.getSystemProperties().put("spring.datasource.password", jsonObj.getString("mysql-password"));
    }
}
