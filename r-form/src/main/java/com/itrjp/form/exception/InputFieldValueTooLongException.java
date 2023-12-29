package com.itrjp.form.exception;

import com.itrjp.form.pojo.model.FormFieldDTO;

import static com.itrjp.form.enums.FormErrorCode.INPUT_FIELD_VALUE_TO_LONG_ERROR;

/**
 * 文本内容过长
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/9/8 16:52
 */
public class InputFieldValueTooLongException extends FormException {
    private final FormFieldDTO field;

    public InputFieldValueTooLongException(FormFieldDTO field) {
        super(INPUT_FIELD_VALUE_TO_LONG_ERROR);
        this.field = field;
    }

    public FormFieldDTO getField() {
        return field;
    }
}
