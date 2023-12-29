package com.itrjp.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itrjp.form.pojo.entity.FormFieldDraft;
import com.itrjp.form.pojo.model.FormDraftDTO;

/**
 * 表单草稿;(form_draft)表服务接口
 *
 * @author : <a [href="mailto:r979668507@gmail.com](mailto:href=%22mailto:r979668507@gmail.com)">renjp</a>
 * @date: 2023-8-14
 */
public interface FormFIeldDraftService extends IService<FormFieldDraft> {

    boolean deleteByFormId(String formId);

    FormDraftDTO getFormDraft(String formId, String userId);

    void deleteByFormId(String formId, String userId);
}
