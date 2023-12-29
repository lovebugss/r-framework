package com.itrjp.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itrjp.form.pojo.entity.FormField;

import java.util.List;
import java.util.Optional;

/**
 * - 表单字段;(form_field)表服务接口
 * - @author : <a [href="mailto:r979668507@gmail.com](mailto:href=%22mailto:r979668507@gmail.com)">renjp</a>
 * - @date: 2023-7-31
 */
public interface FormFieldService extends IService<FormField> {

    Optional<List<FormField>> findByFormId(String id);

    void deleteByFormId(String formId);
}
