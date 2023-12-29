package com.itrjp.form.exception;

import com.itrjp.core.exception.BaseException;

import static com.itrjp.form.enums.FormErrorCode.FORM_UN_SET_FIELD;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/1 11:49
 */
public class FormUnSetFieldException extends BaseException {
    public FormUnSetFieldException() {
        super(FORM_UN_SET_FIELD.getCode(), FORM_UN_SET_FIELD.getMessage());
    }
}
