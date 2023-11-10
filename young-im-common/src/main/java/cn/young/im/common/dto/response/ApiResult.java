package cn.young.im.common.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName ApIResult
 * @Description 接口返回格式
 * @date 2023/9/25 10:17
 * @Author yanceysong
 * @Version 1.0
 */
@AllArgsConstructor
@Data
@ApiModel(description = "api返回值统一格式")
public class ApiResult {

    private static final Integer SUCCESS = 200;
    private static final Integer FAIL = 1;
    private static final Integer ERROR = -1;
    private Object data;
    private String msg;
    private Integer status;

    public static ApiResult success() {
        return new ApiResult(null, "", SUCCESS);
    }

    public static ApiResult success(Object data) {
        return new ApiResult(data, "", SUCCESS);
    }

    public static ApiResult success(Object data, String msg) {
        return new ApiResult(data, msg, SUCCESS);
    }

    public static ApiResult fail(String msg) {
        return new ApiResult(null, msg, FAIL);
    }

    public static ApiResult fail(Object data, String msg) {
        return new ApiResult(data, msg, FAIL);
    }

    public static ApiResult error(String msg) {
        return new ApiResult(null, msg, FAIL);
    }

    public static ApiResult error(Object data, String msg) {
        return new ApiResult(data, msg, FAIL);
    }

    public boolean isSuccess() {
        return status.equals(SUCCESS);
    }
}