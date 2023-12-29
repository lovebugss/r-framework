package com.itrjp.form.handler.field;

import com.itrjp.form.enums.FieldType;
import com.itrjp.form.exception.FormException;
import com.itrjp.form.pojo.model.ExtendedDTO;
import com.itrjp.form.pojo.model.FieldDataDTO;
import com.itrjp.form.pojo.model.FormFieldDTO;
import com.itrjp.form.pojo.model.ImageExtendedDTO;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.itrjp.form.enums.FormErrorCode.FIELD_VALUE_ERROR;
import static com.itrjp.form.enums.FormErrorCode.FIELD_VALUE_LIMIT_ERROR;

/**
 * 图片
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 16:22
 */
@Service
public class ImageFieldHandler extends AbstractFormFieldHandler {


    @Override
    public boolean support(FieldType type) {
        return FieldType.IMAGE.equals(type);
    }

    @Override
    public FieldType supportType() {
        return FieldType.IMAGE;
    }


    @Override
    protected void checkFieldValueType(FormFieldDTO formField, List<FieldDataDTO> value) {
        // 检查地址是否是图片
        String urlStr = value.get(0).getValue();
        String[] split = urlStr.split(",");
        for (String url : split) {
            if (!url.startsWith("http")) {
                throw new FormException(FIELD_VALUE_ERROR);
            }
        }
    }

    @Override
    protected Class<? extends ExtendedDTO> getExtensionSubClass() {
        return ImageExtendedDTO.class;
    }

    @Override
    protected void checkFieldValueHook(FormFieldDTO formField, List<FieldDataDTO> value) {
        ExtendedDTO extended = formField.getExtended();
        int max = ((ImageExtendedDTO) extended).getLimit();
        if (max < value.size()) {
            // 数量超出最大显示
            throw new FormException(FIELD_VALUE_LIMIT_ERROR);
        }

    }

}
