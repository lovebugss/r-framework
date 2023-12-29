package com.itrjp.form.exception;

import com.itrjp.core.exception.BaseException;

import static com.itrjp.form.enums.FormErrorCode.FORM_NOT_FOUND;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/1 11:39
 */
public class FormNotFoundException extends BaseException {
    public FormNotFoundException() {
        super(FORM_NOT_FOUND.getCode(), FORM_NOT_FOUND.getMessage());
    }
}
