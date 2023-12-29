package com.itrjp.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.itrjp.core.utils.JsonUtils;
import com.itrjp.form.mapper.FormFieldDraftMapper;
import com.itrjp.form.pojo.entity.FormFieldDraft;
import com.itrjp.form.pojo.model.FieldDataDTO;
import com.itrjp.form.pojo.model.FormDraftDTO;
import com.itrjp.form.service.FormFIeldDraftService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 表单草稿;(form_draft)表服务实现类
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-8-14
 */
@Service
public class FormFieldDraftServiceImpl extends ServiceImpl<FormFieldDraftMapper, FormFieldDraft> implements FormFIeldDraftService {

    @Override
    public boolean deleteByFormId(String formId) {
        if (formId == null) {
            return false;
        }
        LambdaQueryWrapper<FormFieldDraft> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FormFieldDraft::getFormId, formId);
        return this.baseMapper.delete(queryWrapper) > 0;
    }

    @Override
    public FormDraftDTO getFormDraft(String formId, String userId) {
        LambdaQueryWrapper<FormFieldDraft> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FormFieldDraft::getFormId, formId);
        queryWrapper.eq(FormFieldDraft::getUserId, userId);
        List<FormFieldDraft> formFieldDrafts = this.baseMapper.selectList(queryWrapper);
        if (formFieldDrafts.isEmpty()) {
            return null;
        }
        return new FormDraftDTO(formFieldDrafts.stream().collect(Collectors.toMap(FormFieldDraft::getFieldKey, formFieldDraft -> {
            return JsonUtils.parse(formFieldDraft.getFieldValue(), new TypeReference<List<FieldDataDTO>>() {
            });
        })));
    }

    @Override
    public void deleteByFormId(String formId, String userId) {
        this.baseMapper.delete(new LambdaQueryWrapper<FormFieldDraft>().eq(FormFieldDraft::getFormId, formId).eq(FormFieldDraft::getUserId, userId));
    }
}
