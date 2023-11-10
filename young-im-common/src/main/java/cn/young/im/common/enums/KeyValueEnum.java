package cn.young.im.common.enums;

import java.io.Serializable;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description K - V 形式枚举接口
 * @date 2023/11/10
 */
public interface KeyValueEnum<K, V> extends Serializable {

    /**
     * 获取 code
     */
    K getCode();

    /**
     * 获取描述
     */
    V getDesc();
}
