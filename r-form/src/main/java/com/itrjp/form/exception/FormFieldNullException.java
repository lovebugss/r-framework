package com.itrjp.form.exception;

import com.itrjp.core.exception.BaseException;

import static com.itrjp.form.enums.FormErrorCode.FORM_FIELD_IS_NULL;

/**
 * 表单字段为空
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 17:42
 */
public class FormFieldNullException extends BaseException {
    private final String fieldName;

    public FormFieldNullException(String fieldName) {
        super(FORM_FIELD_IS_NULL.getCode(), fieldName + ": " + FORM_FIELD_IS_NULL.getMessage());
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
