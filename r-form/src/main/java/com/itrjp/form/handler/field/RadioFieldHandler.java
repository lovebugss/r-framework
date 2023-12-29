package com.itrjp.form.handler.field;

import com.itrjp.form.enums.FieldType;
import com.itrjp.form.pojo.model.FieldDataDTO;
import com.itrjp.form.pojo.model.FormFieldDTO;
import com.itrjp.form.service.FormFieldOptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单选
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 16:22
 */
@Slf4j
@Service
public class RadioFieldHandler extends OptionFieldHandler {

    public RadioFieldHandler(FormFieldOptionService formFieldOptionService) {
        super(formFieldOptionService);
    }

    @Override
    public boolean support(FieldType type) {
        return FieldType.RADIO.equals(type);
    }

    @Override
    public FieldType supportType() {
        return FieldType.RADIO;
    }


    @Override
    public void checkFieldValue(FormFieldDTO formField, List<FieldDataDTO> fieldData) {
        log.info("radio field check value:{}", fieldData);
        super.checkFieldValue(formField, fieldData);
    }

}
