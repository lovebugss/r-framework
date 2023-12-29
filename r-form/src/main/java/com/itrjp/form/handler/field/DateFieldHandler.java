package com.itrjp.form.handler.field;

import com.itrjp.form.enums.FieldType;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 16:24
 */
@Service
public class DateFieldHandler extends AbstractFormFieldHandler {
    @Override
    public boolean support(FieldType type) {
        return FieldType.DATE.equals(type);
    }

    @Override
    public FieldType supportType() {
        return FieldType.DATE;
    }

}
