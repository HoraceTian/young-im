package cn.young.im.springboot.starter.adapter.registry;

import cn.young.im.common.enums.KeyValueEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status implements KeyValueEnum<Integer, String> {

    HEALTH(1, "健康"),

    SUBJECTIVE_DOWN_LINE(2, "主观下线"),

    OBJECTIVE_DOWN(3, "客观下线");


    final int code;

    final String desc;


    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}