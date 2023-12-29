package com.itrjp.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itrjp.form.pojo.entity.FormFieldOption;
import com.itrjp.form.pojo.model.FieldOptionDTO;

import java.util.List;

/**
 * - 表单字段选项;(form_field_option)表服务接口
 * - @author : <a [href="mailto:r979668507@gmail.com](mailto:href=%22mailto:r979668507@gmail.com)">renjp</a>
 * - @date: 2023-7-31
 */
public interface FormFieldOptionService extends IService<FormFieldOption> {

    List<FieldOptionDTO> getOptionByFieldKey(String fieldKey);

    void deleteByFormId(String formId);
}
