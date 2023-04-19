package com.rhq.parkinglotsystem.common;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * 相应结果集
 * @param <T>
 */
@Configuration
@Data
public class ResponseResult<T> {
    private long code;
    private String message;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ResponseResult<T> Success(T data){
        return new ResponseResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> ResponseResult<T> Success(T data, String message) {
        return new ResponseResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> ResponseResult<T> Failed(IErrorCode errorCode) {
        return new ResponseResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> ResponseResult<T> Failed(String message) {
        return new ResponseResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResponseResult<T> Failed() {
        return Failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ResponseResult<T> validateFailed() {
        return Failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> ResponseResult<T> validateFailed(String message) {
        return new ResponseResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ResponseResult<T> unauthorized(T data) {
        return new ResponseResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ResponseResult<T> forbidden(T data) {
        return new ResponseResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }
}
