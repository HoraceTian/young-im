package cn.young.im.springboot.starter.adapter.config.parse;

import cn.young.im.springboot.starter.adapter.config.ConfigType;
import cn.young.im.springboot.starter.adapter.config.parse.handler.JsonConfigParseHandler;
import cn.young.im.springboot.starter.adapter.config.parse.handler.PropertiesConfigParseHandler;
import cn.young.im.springboot.starter.adapter.config.parse.handler.YamlConfigParseHandler;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.synchronizedMap;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 配置解析处理器工厂
 * @date 2023/12/16
 */
public class ConfigParseFactory {
    private static final Map<String, IConfigParseHandler> handlers = synchronizedMap(new LinkedHashMap<>());

    static {
        handlers.put(ConfigType.JSON.getType(), new JsonConfigParseHandler());
        handlers.put(ConfigType.PROPERTIES.getType(), new PropertiesConfigParseHandler());
        handlers.put(ConfigType.YAML.getType(), new YamlConfigParseHandler());
    }

    public static IConfigParseHandler acquireHandler(String type){
        return handlers.get(type);
    }
}
