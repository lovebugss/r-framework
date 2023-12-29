package com.itrjp.form.handler.field;

import com.itrjp.form.pojo.model.FieldDataDTO;
import com.itrjp.form.pojo.model.FormConfigDTO;
import com.itrjp.form.pojo.model.FormFieldDTO;

import java.util.List;

/**
 * 字段检查器
 *
 * @param <T> 字段类型
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 16:18
 */
public interface FormFieldChecker extends FieldMatcher {

    /**
     * 检查表单字段
     *
     * @param formId
     * @param config
     * @param formFieldDTO
     */
    void checkAndSetField(String formId, FormConfigDTO config, FormFieldDTO formFieldDTO);

    /**
     * 检查表单字段值
     */
    void checkFieldValue(FormFieldDTO formField, List<FieldDataDTO> value);
}
