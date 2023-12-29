package com.itrjp.form.handler.field;

import com.itrjp.form.enums.FieldType;
import com.itrjp.form.exception.InputFieldValueTooLongException;
import com.itrjp.form.pojo.model.ExtendedDTO;
import com.itrjp.form.pojo.model.FieldDataDTO;
import com.itrjp.form.pojo.model.FormFieldDTO;
import com.itrjp.form.pojo.model.InputExtendedDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文本框
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 16:22
 */
@Service
@Slf4j
public class InputFieldHandler extends AbstractFormFieldHandler {
    @Override
    public boolean support(FieldType type) {
        return FieldType.INPUT.equals(type);
    }

    @Override
    public FieldType supportType() {
        return FieldType.INPUT;
    }


    @Override
    protected Class<? extends ExtendedDTO> getExtensionSubClass() {
        return InputExtendedDTO.class;
    }

    protected void checkFieldValueHook(FormFieldDTO formField, List<FieldDataDTO> value) {
        FieldDataDTO fieldDataDTO = value.get(0);
        String valueStr = fieldDataDTO.getValue();
        InputExtendedDTO extended = (InputExtendedDTO) formField.getExtended();
        if ((extended.isMultiline() && valueStr.length() > 200) || (valueStr.length() > 50)) {

            throw new InputFieldValueTooLongException(formField);
        }
    }
}
