package cn.young.im.springboot.starter.adapter.config.parse.handler;

import cn.young.im.springboot.starter.adapter.config.parse.IConfigParseHandler;
import com.esotericsoftware.yamlbeans.YamlReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

/**
 * @ClassName YamlConfigParseHandler
 * @Description
 * @date 2023/12/18 10:00
 * @Author yanceysong
 * @Version 1.0
 */
@Slf4j
public class YamlConfigParseHandler implements IConfigParseHandler {

    @Override
    public void parse(String content, Object bean) {
        try (YamlReader reader = new YamlReader(content)) {
            Object sourceBean = reader.read(bean.getClass());
            BeanUtils.copyProperties(sourceBean, bean);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
