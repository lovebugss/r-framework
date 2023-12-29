package com.itrjp.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itrjp.form.covert.FormCovert;
import com.itrjp.form.mapper.FormFieldOptionMapper;
import com.itrjp.form.pojo.entity.FormFieldOption;
import com.itrjp.form.pojo.model.FieldOptionDTO;
import com.itrjp.form.service.FormFieldOptionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表单字段选项;(form_field_option)表服务实现类
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@Service
public class FormFieldOptionServiceImpl extends ServiceImpl<FormFieldOptionMapper, FormFieldOption> implements FormFieldOptionService {
    @Override
    public List<FieldOptionDTO> getOptionByFieldKey(String fieldKey) {
        LambdaQueryWrapper<FormFieldOption> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FormFieldOption::getFieldKey, fieldKey);
        List<FormFieldOption> list = list(queryWrapper);
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        // 类型转换
        return list.stream()
                .map(FormCovert.INSTANCE::toFormFieldOptionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByFormId(String formId) {
        if (formId == null) {
            return;
        }
        this.baseMapper.delete(new LambdaQueryWrapper<FormFieldOption>().eq(FormFieldOption::getFormId, formId));
    }
}
