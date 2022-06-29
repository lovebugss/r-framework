package com.itrjp.core.exception;

import com.itrjp.core.result.ErrorCode;

/**
 * 自定义业务异常
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/23 16:08
 */
public class BizException extends RuntimeException implements ErrorCode {
    private final int code;

    public BizException(int code, String message) {
        super(message);
        this.code = code;

    }

    @Override
    public int getCode() {
        return code;
    }
}
