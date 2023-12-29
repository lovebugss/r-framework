package com.itrjp.form.exception;

import com.itrjp.core.exception.BaseException;
import com.itrjp.form.enums.FormErrorCode;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/21 18:15
 */
public class FieldKeyNotExitsException extends BaseException {
    private final String key;

    public FieldKeyNotExitsException(String key) {
        super(FormErrorCode.FIELD_KEY_NOT_EXITS.getCode(), String.format(FormErrorCode.FIELD_KEY_NOT_EXITS.getMessage(), key));
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
