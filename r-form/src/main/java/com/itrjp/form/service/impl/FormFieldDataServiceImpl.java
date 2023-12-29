package com.itrjp.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itrjp.form.mapper.FormFieldDataMapper;
import com.itrjp.form.pojo.entity.FormFieldData;
import com.itrjp.form.service.FormFieldDataService;
import org.springframework.stereotype.Service;

/**
 * 表单字段值;(form_field_data)表服务实现类
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@Service
public class FormFieldDataServiceImpl extends ServiceImpl<FormFieldDataMapper, FormFieldData> implements FormFieldDataService {
    @Override
    public void deleteByFormId(String formId) {
        this.baseMapper.delete(new LambdaQueryWrapper<FormFieldData>().eq(FormFieldData::getFormId, formId));
    }
}
