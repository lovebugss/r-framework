package com.itrjp.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itrjp.form.mapper.FormDiseaseMapper;
import com.itrjp.form.pojo.entity.FormDisease;
import com.itrjp.form.service.FormDiseaseService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 表单病种映射;(form_disease)表服务实现类
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-8-2
 */
@Service
public class FormDiseaseServiceImpl extends ServiceImpl<FormDiseaseMapper, FormDisease> implements FormDiseaseService {
    @Override
    public List<FormDisease> getByFormIds(List<String> formIds) {
        if (formIds != null && !formIds.isEmpty()) {
            LambdaQueryWrapper<FormDisease> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(FormDisease::getFormId, formIds);
            return this.list(queryWrapper);
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteByFormId(String formId) {
        LambdaQueryWrapper<FormDisease> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FormDisease::getFormId, formId);
        this.remove(queryWrapper);
    }
}
