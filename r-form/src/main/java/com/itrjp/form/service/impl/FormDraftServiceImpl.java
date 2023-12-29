package com.itrjp.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itrjp.form.mapper.FormDraftMapper;
import com.itrjp.form.pojo.entity.FormDraft;
import com.itrjp.form.service.FormDraftService;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/14 17:24
 */
@Service
public class FormDraftServiceImpl extends ServiceImpl<FormDraftMapper, FormDraft> implements FormDraftService {

    @Override
    public void deleteByFormId(String formId, String userId) {
        this.baseMapper.delete(new LambdaQueryWrapper<FormDraft>().eq(FormDraft::getFormId, formId).eq(FormDraft::getUserId, userId));
    }
}
