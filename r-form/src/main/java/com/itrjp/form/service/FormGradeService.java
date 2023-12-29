package com.itrjp.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itrjp.form.pojo.entity.FormGrade;
import com.itrjp.form.pojo.model.FormGradeDTO;

import java.util.List;

/**
 * - 表单评分标准;(form_grade)表服务接口
 * - @author : <a [href="mailto:r979668507@gmail.com](mailto:href=%22mailto:r979668507@gmail.com)">renjp</a>
 * - @date: 2023-7-31
 */
public interface FormGradeService extends IService<FormGrade> {

    List<FormGradeDTO> selectByFormId(String id);

    void deleteByFormId(String formId);
}
