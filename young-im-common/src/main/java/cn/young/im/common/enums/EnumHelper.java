package cn.young.im.common.enums;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 枚举提取工具类
 * @date 2023/11/10
 */
@Slf4j
public class EnumHelper {

    public static final String BIZ_CODE_METHOD_NAME = "getCode";

    public static <T extends Enum<T>> T getEnumByCode(Class<T> clazz, Object code) {
        try {
            Method method = clazz.getMethod(BIZ_CODE_METHOD_NAME);

            EnumSet<T> enumSet = EnumSet.allOf(clazz);
            for (T t : enumSet) {
                Object ret = method.invoke(t);
                if (Objects.equals(ret, code)) {
                    return t;
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("Failed to extract the enumeration instance");
            return null;
        }
        return null;
    }
}
