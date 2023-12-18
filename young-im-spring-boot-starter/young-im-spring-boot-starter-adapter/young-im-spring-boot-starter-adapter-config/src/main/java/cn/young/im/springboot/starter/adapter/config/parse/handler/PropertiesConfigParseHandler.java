package cn.young.im.springboot.starter.adapter.config.parse.handler;

import cn.hutool.setting.dialect.Props;
import cn.young.im.springboot.starter.adapter.config.parse.IConfigParseHandler;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

import java.io.StringReader;
import java.util.Properties;

/**
 * @ClassName TextConfigParseHandler
 * @Description
 * @date 2023/12/18 10:01
 * @Author yanceysong
 * @Version 1.0
 */
public class PropertiesConfigParseHandler implements IConfigParseHandler {

    @Override
    @SneakyThrows
    public void parse(String content, Object bean) {
        Properties properties = new Properties();
        properties.load(new StringReader(content));
        Props props = new Props(properties);
        Object sourceBean = props.toBean(bean.getClass());
        BeanUtils.copyProperties(sourceBean, bean);
    }
}
