package com.itrjp.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itrjp.form.pojo.entity.FormDepartment;

import java.util.List;

/**
 * 表单科室映射;(form_department)表服务接口
 *
 * @author : <a [href="mailto:r979668507@gmail.com](mailto:href=%22mailto:r979668507@gmail.com)">renjp</a>
 * @date: 2023-8-2
 */
public interface FormDepartmentService extends IService<FormDepartment> {

    List<FormDepartment> getByFormIds(List<String> formIds);

    void deleteByFormId(String formId);
}
