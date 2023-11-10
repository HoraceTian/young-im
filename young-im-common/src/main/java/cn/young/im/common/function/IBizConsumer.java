package cn.young.im.common.function;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 业务函数方接口定义
 * @date 2023/11/10
 */
@FunctionalInterface
public interface IBizConsumer<T, U> {

    /**
     * 双参数无返回值
     */
    void consumer(T t, U u);
}
