package cn.young.im.common.dto.response;

import cn.young.im.common.enums.KeyValueEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 作者：沈自在 <a href="https://www.szz.tax">Blog</a>
 *
 * @description 响应包装器
 * @date 2023/11/10
 */
@Getter
@Setter
public class ResultWrapper<T> {

    private final static String SUCCESS = "SUCCESS";
    private final static Integer DEFAULT_SUCCESS_CODE = 200;

    private Integer status;
    private String msg;
    private T data;

    public ResultWrapper() {
    }

    public ResultWrapper(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess(){
        return Objects.equals(status, DEFAULT_SUCCESS_CODE);
    }

    public static <T> ResultWrapper<T> success() {
        ResultWrapper<T> resultWrapper = new ResultWrapper<>();
        resultWrapper.setStatus(DEFAULT_SUCCESS_CODE);
        resultWrapper.setMsg(SUCCESS);

        return resultWrapper;
    }

    public static <T> ResultWrapper<T> success(T data) {
        ResultWrapper<T> resultWrapper = success();
        resultWrapper.setData(data);
        return resultWrapper;
    }


    public static ResultWrapper<String> fail(String message) {
        ResultWrapper<String> resultWrapper = new ResultWrapper<>();
        resultWrapper.setStatus(500);
        resultWrapper.setMsg(message);
        return resultWrapper;
    }

    public static ResultWrapper<String> fail(KeyValueEnum<Integer, String> e) {
        ResultWrapper<String> resultWrapper = new ResultWrapper<>();
        resultWrapper.setStatus(e.getCode());
        resultWrapper.setMsg(e.getDesc());
        return resultWrapper;
    }

    public static <T> ResultWrapper<T> fail(KeyValueEnum<Integer, String> e, T data) {
        ResultWrapper<T> resultWrapper = new ResultWrapper<>();
        resultWrapper.setStatus(e.getCode());
        resultWrapper.setMsg(e.getDesc());
        resultWrapper.setData(data);
        return resultWrapper;
    }

    public static ResultWrapper<String> wrapBol(boolean success) {
        return success ? success() : fail("operation failed, please try again!");
    }
}