package com.itrjp.form.handler.field;

import com.itrjp.form.covert.FormCovert;
import com.itrjp.form.pojo.entity.FormField;
import com.itrjp.form.pojo.model.ExtendedDTO;
import com.itrjp.form.pojo.model.FormFieldDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/25 16:31
 */
@Slf4j
public abstract class AbstractFieldParser implements FieldParser {
    @Override
    public FormFieldDTO toFormFieldDTO(FormField formField) {
        FormFieldDTO formFieldDTO = FormCovert.INSTANCE.toFormFieldDTO(formField);
        // 数据库中的字段类型为varchat. 这里转成具体实体 , 由子类实现. 不同的类型转换成不同的实体
        formFieldDTO.setExtended(convertExtensionField(formField));
        return formFieldDTO;
    }

    @Override
    public FormField toFormField(FormFieldDTO dto) {
        return null;
    }

    /**
     * 钩子函数. 完成字段扩展属性的转换
     *
     * @param extended
     * @return
     */
    protected ExtendedDTO convertExtensionField(FormField extended) {
        Class<? extends ExtendedDTO> subClass = getExtensionSubClass();
        if (subClass == null) {
            return null;
        }
        try {
            if (log.isDebugEnabled()) {
                log.debug("字段扩展属性转换:{}, class: {}", extended, subClass);
            }
            return JsonUtil.toObject(extended.getExtended(), subClass);
        } catch (Exception e) {
            log.warn("字段扩展属性转换失败:{}", extended, e);
        }
        return null;
    }

    protected Class<? extends ExtendedDTO> getExtensionSubClass() {
        return null;
    }
}
