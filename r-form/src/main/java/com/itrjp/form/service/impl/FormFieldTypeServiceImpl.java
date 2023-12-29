package com.itrjp.form.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itrjp.form.mapper.FormFieldTypeMapper;
import com.itrjp.form.pojo.entity.FormFieldType;
import com.itrjp.form.service.FormFieldTypeService;
import org.springframework.stereotype.Service;

/**
 * 表单字段类型;(form_field_type)表服务实现类
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@Service
public class FormFieldTypeServiceImpl extends ServiceImpl<FormFieldTypeMapper, FormFieldType> implements FormFieldTypeService {
}
