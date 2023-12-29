package com.itrjp.form.exception;

import com.itrjp.core.exception.BaseException;
import com.itrjp.form.enums.FormErrorCode;

/**
 *
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/17 13:45
 */
public class FormException extends BaseException {
    public FormException(FormErrorCode errorCode) {
        super(errorCode);
    }

    protected FormException(int code, String message) {
        super(code, message);
    }
}
