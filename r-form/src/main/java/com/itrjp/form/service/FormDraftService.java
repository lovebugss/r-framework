package com.itrjp.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itrjp.form.pojo.entity.FormDraft;

/**
 *
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/14 17:24
 */
public interface FormDraftService extends IService<FormDraft> {

    void deleteByFormId(String formId, String userId);
}
