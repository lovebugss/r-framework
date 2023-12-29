package com.itrjp.form.exception;

import com.itrjp.form.enums.FormErrorCode;

/**
 *
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/9/7 14:20
 */
public class FieldScoreErrorException extends FormException {
    private final String label;

    public FieldScoreErrorException(String label) {
        super(FormErrorCode.FORM_FIELD_SCORE_ERROR.getCode(), FormErrorCode.FORM_FIELD_SCORE_ERROR.getMessage() + ": " + label);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
