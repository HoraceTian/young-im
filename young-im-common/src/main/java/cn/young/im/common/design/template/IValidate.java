package cn.young.im.common.design.template;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 模版方法参数验证
 * @date 2023/11/10
 */
public interface IValidate<R, P> {
    /**
     * 参数验证
     */
    R validate(P param);
}