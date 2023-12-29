package com.itrjp.form.handler.field;

import com.itrjp.form.pojo.entity.FormField;
import com.itrjp.form.pojo.model.FormFieldDTO;

/**
 * 字段解析器, 主要提供FormFieldDTO <--> FormField的相互转换
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/17 16:16
 */
public interface FieldParser {

    FormFieldDTO toFormFieldDTO(FormField formField);


    FormField toFormField(FormFieldDTO dto);
}
