package com.itrjp.form.handler.field;

import com.itrjp.form.pojo.entity.FormField;
import com.itrjp.form.pojo.model.FieldDataDTO;
import com.itrjp.form.pojo.model.FormFieldDTO;

import java.util.List;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 17:06
 */
public interface FormFieldHandler extends FormFieldChecker {
    FormFieldDTO getFormField(FormField formField);

    int calculateScore(FormFieldDTO formField, List<FieldDataDTO> value);
}
