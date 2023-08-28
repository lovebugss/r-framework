package com.itrjp.cloud.core.result;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * REST Result
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/23 15:44
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public final class Result<T> {
    private final int code;
    private final String message;
    private final Long timestamp = System.currentTimeMillis();
    private final T data;

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result<Void> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(GlobalErrorCode.SUCCESS.getCode(), GlobalErrorCode.SUCCESS.getMessage(), data);
    }

    public static Result<Void> error() {
        return error(GlobalErrorCode.ERROR);
    }

    public static Result<Void> error(ErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static Result<Void> error(int code, String message) {
        return new Result<>(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }
}
