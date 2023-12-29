package com.itrjp.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itrjp.form.pojo.entity.FormFieldData;

/**
 * - 表单字段值;(form_field_data)表服务接口
 * - @author : <a [href="mailto:r979668507@gmail.com](mailto:href=%22mailto:r979668507@gmail.com)">renjp</a>
 * - @date: 2023-7-31
 */
public interface FormFieldDataService extends IService<FormFieldData> {

    void deleteByFormId(String formId);
}
