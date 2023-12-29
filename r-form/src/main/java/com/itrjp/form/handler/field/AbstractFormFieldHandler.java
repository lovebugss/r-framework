package com.itrjp.form.handler.field;

import cn.hutool.core.collection.CollUtil;
import com.itrjp.core.utils.UUIDUtils;
import com.itrjp.form.covert.FormCovert;
import com.itrjp.form.enums.FieldType;
import com.itrjp.form.enums.FormType;
import com.itrjp.form.exception.FieldScoreErrorException;
import com.itrjp.form.exception.FormFieldNullException;
import com.itrjp.form.pojo.entity.FormField;
import com.itrjp.form.pojo.model.ExtendedDTO;
import com.itrjp.form.pojo.model.FieldDataDTO;
import com.itrjp.form.pojo.model.FormConfigDTO;
import com.itrjp.form.pojo.model.FormFieldDTO;
import com.itrjp.hellocare.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 表单字段处理器
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 17:39
 */
@Slf4j
public abstract class AbstractFormFieldHandler implements FormFieldHandler {

    @Override
    public void checkFieldValue(FormFieldDTO formField, List<FieldDataDTO> value) {
        // 检查必填
        checkFieldValueRequired(formField, value);
        if (CollUtil.isNotEmpty(value)) {
            // 检查字段值类型
            checkFieldValueType(formField, value);
            // 钩子函数, 其他检查
            checkFieldValueHook(formField, value);
        }
    }

    @Override
    public void checkAndSetField(String formId, FormConfigDTO config, FormFieldDTO formFieldDTO) {
        // 生成唯一的key 8位
        String fieldKey = UUIDUtils.generateShortUuid();
        formFieldDTO.setKey(fieldKey);

        FormType type = config.getType();
        if (FormType.SCORE.equals(type)) {
            // 选择题不需要检查字段分支, 而是检查选项分值
            FieldType fieldType = formFieldDTO.getType();
            if (!(FieldType.SELECT.equals(fieldType) || FieldType.RADIO.equals(fieldType)) && isScoreError(formFieldDTO.getScore())) {
                log.warn("表单字段分值错误:{}", formFieldDTO);
                throw new FieldScoreErrorException(formFieldDTO.getLabel());
            }
        } else {
            formFieldDTO.setScore(null);
        }
    }

    protected boolean isScoreError(Integer score) {
        return score == null || score <= 0;
    }

    /**
     * 钩子函数, 子类实现一些其他检查
     *
     * @param formField
     * @param value
     */
    protected void checkFieldValueHook(FormFieldDTO formField, List<FieldDataDTO> value) {

    }

    protected void checkFieldValueType(FormFieldDTO formField, List<FieldDataDTO> value) {
    }

    protected void checkFieldValueRequired(FormFieldDTO formField, List<FieldDataDTO> value) {
        if (Boolean.TRUE.equals(formField.isRequired()) && CollUtil.isEmpty(value)) {
            throw new FormFieldNullException(formField.getLabel());
        }
    }

    @Override
    public FormFieldDTO getFormField(FormField formField) {
        FormFieldDTO formFieldDTO = FormCovert.INSTANCE.toFormFieldDTO(formField);
        // 数据库中的字段类型为varchat. 这里转成具体实体 , 由子类实现. 不同的类型转换成不同的实体
        formFieldDTO.setExtended(convertExtensionField(formField));
        return formFieldDTO;
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
            return JsonUtils.toObject(extended.getExtended(), subClass);
        } catch (Exception e) {
            log.warn("字段扩展属性转换失败:{}", extended, e);
        }
        return null;
    }

    protected Class<? extends ExtendedDTO> getExtensionSubClass() {
        return null;
    }

    @Override
    public int calculateScore(FormFieldDTO formField, List<FieldDataDTO> value) {
        log.info("1111: {}. {}", formField, value);
        //TODO  计算分值， 空指针Aer0SHdy， UB1dp9kn
        return value != null && !value.isEmpty() ? formField.getScore() : 0;
    }
}
