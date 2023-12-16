package cn.young.im.springboot.starter.adapter.config;

import com.alibaba.nacos.api.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public enum ConfigType {

    /**
     * Json
     */
    JSON("json"),

    /**
     * text
     */
    TEXT("text"),

    /**
     * yaml
     */
    YAML("yaml"),

    /**
     *
     */
    UNSET("unset");

    private final String type;

    private static final Map<String, ConfigType> LOCAL_MAP = new HashMap<>();

    static {
        for (ConfigType configType : values()) {
            LOCAL_MAP.put(configType.getType(), configType);
        }
    }

    ConfigType(String type) {
        this.type = type;
    }

    /**
     * 获取类型
     */
    public String getType() {
        return type;
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
