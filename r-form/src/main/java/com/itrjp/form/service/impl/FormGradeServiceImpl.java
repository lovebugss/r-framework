package com.itrjp.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itrjp.form.mapper.FormGradeMapper;
import com.itrjp.form.pojo.entity.FormGrade;
import com.itrjp.form.pojo.model.FormGradeDTO;
import com.itrjp.form.service.FormGradeService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 表单评分标准;(form_grade)表服务实现类
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@Service
public class FormGradeServiceImpl extends ServiceImpl<FormGradeMapper, FormGrade> implements FormGradeService {
    @Override
    public List<FormGradeDTO> selectByFormId(String id) {
        if (id == null) {
            return Collections.emptyList();
        }
        return this.baseMapper.selectByFormId(id);
    }

    @Override
    public void deleteByFormId(String formId) {
        if (formId == null) {
            return;
        }
        this.baseMapper.delete(new LambdaQueryWrapper<FormGrade>().eq(FormGrade::getFormId, formId));
    }
}
