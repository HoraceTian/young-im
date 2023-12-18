package cn.young.im.springboot.starter.adapter.config;

import com.alibaba.nacos.api.utils.StringUtils;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@Getter
public enum ConfigType {

    /**
     * Json
     */
    JSON("json"),

    /**
     * text
     */
    PROPERTIES("properties"),

    /**
     * yaml
     */
    YAML("yaml"),

    /**
     *
     */
    UNSET("unset");

    private static final Map<String, ConfigType> LOCAL_MAP = new HashMap<>();

    static {
        for (ConfigType configType : values()) {
            LOCAL_MAP.put(configType.getType(), configType);
        }
    }

    private final String type;

    ConfigType(String type) {
        this.type = type;
    }

    /**
     * 获取默认类型
     */
    public static ConfigType getDefaultType() {
        return YAML;
    }

    /**
     * 判断类型是否合法
     */
    public static Boolean isValidType(String type) {
        if (StringUtils.isBlank(type)) {
            return false;
        }
        return null != LOCAL_MAP.get(type);
    }
}
