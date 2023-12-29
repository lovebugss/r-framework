package com.itrjp.form.handler.field;

import com.itrjp.form.enums.FieldType;
import com.itrjp.form.enums.FormErrorCode;
import com.itrjp.form.exception.FormException;
import com.itrjp.form.pojo.model.ExtendedDTO;
import com.itrjp.form.pojo.model.FieldDataDTO;
import com.itrjp.form.pojo.model.FormFieldDTO;
import com.itrjp.form.pojo.model.ScoreExtendedDTO;
import com.itrjp.form.service.FormFieldOptionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 16:23
 */
@Slf4j
@Service
public class ScoreFieldHandler extends AbstractFormFieldHandler {
    private final FormFieldOptionService formFieldOptionService;

    public ScoreFieldHandler(FormFieldOptionService formFieldOptionService) {
        this.formFieldOptionService = formFieldOptionService;
    }

    @Override
    public boolean support(FieldType type) {
        return FieldType.SCORE.equals(type);
    }

    @Override
    public FieldType supportType() {
        return FieldType.SCORE;
    }


    @Override
    protected void checkFieldValueType(FormFieldDTO formField, List<FieldDataDTO> value) {
        if (!StringUtils.isNumeric(value.get(0).getValue())) {
            throw new FormException(FormErrorCode.FIELD_VALUE_TYPE_ERROR);
        }
    }

    @Override
    protected void checkFieldValueHook(FormFieldDTO formField, List<FieldDataDTO> fieldData) {
        String value = fieldData.get(0).getValue();
        int i = Integer.parseInt(value);

        ExtendedDTO extended = formField.getExtended();
        if (i > ((ScoreExtendedDTO) extended).getMaxScore()) {
            // 超过最大分值
            throw new FormException(FormErrorCode.FIELD_VALUE_LIMIT_ERROR);
        }
    }

    @Override
    protected Class<? extends ExtendedDTO> getExtensionSubClass() {
        return ScoreExtendedDTO.class;
    }
}
