package cn.young.im.common.design.template;

import cn.young.im.common.dto.response.ResultWrapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 模版方式基础
 * @date 2023/11/10
 */
@Slf4j
@Getter
public abstract class AbstractTemplateOperate<T, P> implements Operate<T, P> {
    private final String logicName;
    private final String errorCode;

    protected AbstractTemplateOperate() {
        this(null, null);
    }

    protected AbstractTemplateOperate(String logicName, String errorCode) {
        this.logicName = logicName;
        this.errorCode = errorCode;
    }

    /**
     * 扩展点：参数验证
     */
    protected void validation(P arg, ResultWrapper<T> result) {
    }

    /**
     * 扩展点：具体业务
     */
    protected abstract ResultWrapper<T> processor(P param, ResultWrapper<T> result);

    /**
     * 扩展点：结果封装
     */
    protected void result(P param, ResultWrapper<T> result) {
    }

    /**
     * 扩展点：异常补充
     */
    protected void exceptionDetail(P arg, ResultWrapper<T> result, Exception exception) {

    }

    @Override
    public ResultWrapper<T> operate(P param) {
        ResultWrapper<T> res = ResultWrapper.success();

        try {
            this.validation(param, res);

            if (!res.isSuccess()) {
                return res;
            }

            res = this.processor(param, res);
            if (!res.isSuccess()) {
                return res;
            }

            this.result(param, res);
        } catch (Exception ex) {
            log.error("process {} exception, params: {}", logicName, param);
        }
        return res;
    }
}