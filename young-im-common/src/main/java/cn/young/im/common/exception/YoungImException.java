package cn.young.im.common.exception;

/**
 * @ClassName YoungImExcrption
 * @Description
 * @date 2023/11/13 10:30
 * @Author yanceysong
 * @Version 1.0
 */
public class YoungImException extends RuntimeException{
    public YoungImException(String errorMsg) {
        super(errorMsg);
    }
}