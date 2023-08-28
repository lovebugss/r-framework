package com.itrjp.cloud.core.result;

/**
 * 全局异常错误码
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/29 16:20
 */
public enum GlobalErrorCode implements ErrorCode {


    SUCCESS(200, "success"), ERROR(0, "error"), SERVER_ERROR(500, "服务器异常, 请稍后重试");
    private final int code;
    private final String message;

    GlobalErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
