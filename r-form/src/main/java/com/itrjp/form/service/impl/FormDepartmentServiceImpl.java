package com.itrjp.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itrjp.form.mapper.FormDepartmentMapper;
import com.itrjp.form.pojo.entity.FormDepartment;
import com.itrjp.form.service.FormDepartmentService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 表单科室映射;(form_department)表服务实现类
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-8-2
 */
@Service
public class FormDepartmentServiceImpl extends ServiceImpl<FormDepartmentMapper, FormDepartment> implements FormDepartmentService {
    @Override
    public List<FormDepartment> getByFormIds(List<String> formIds) {
        if (formIds != null && !formIds.isEmpty()) {
            LambdaQueryWrapper<FormDepartment> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(FormDepartment::getFormId, formIds);
            return list(queryWrapper);
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteByFormId(String formId) {
        LambdaQueryWrapper<FormDepartment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FormDepartment::getFormId, formId);
        remove(queryWrapper);
    }
}
