package com.itrjp.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itrjp.form.pojo.entity.FormDisease;

import java.util.List;

/**
 * 表单病种映射;(form_disease)表服务接口
 *
 * @author : <a [href="mailto:r979668507@gmail.com](mailto:href=%22mailto:r979668507@gmail.com)">renjp</a>
 * @date: 2023-8-2
 */
public interface FormDiseaseService extends IService<FormDisease> {

    List<FormDisease> getByFormIds(List<String> formIds);

    void deleteByFormId(String formId);
}
