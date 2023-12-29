package com.itrjp.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itrjp.form.mapper.FormFieldMapper;
import com.itrjp.form.pojo.entity.FormField;
import com.itrjp.form.service.FormFieldService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 表单字段;(form_field)表服务实现类
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@Service
public class FormFieldServiceImpl extends ServiceImpl<FormFieldMapper, FormField> implements FormFieldService {
    @Override
    public void deleteByFormId(String formId) {
        this.baseMapper.delete(new LambdaQueryWrapper<FormField>().eq(FormField::getFormId, formId));
    }

    @Override
    public Optional<List<FormField>> findByFormId(String id) {


        return Optional.of(this.baseMapper.findByFormId(id));
    }
}
