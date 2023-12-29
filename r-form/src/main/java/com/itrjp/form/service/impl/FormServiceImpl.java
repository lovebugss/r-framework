package com.itrjp.form.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itrjp.form.mapper.FormMapper;
import com.itrjp.form.pojo.entity.Form;
import com.itrjp.form.service.FormService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/31 19:24
 */
@Service
public class FormServiceImpl extends ServiceImpl<FormMapper, Form> implements FormService {
    @Override
    public void addFormCollectCount(String formId) {
        this.baseMapper.addFormCollectCount(formId);
    }
}
