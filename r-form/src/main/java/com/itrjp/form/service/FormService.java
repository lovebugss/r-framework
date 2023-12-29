package com.itrjp.form.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itrjp.form.pojo.entity.Form;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/31 19:23
 */
public interface FormService extends IService<Form> {
    void addFormCollectCount(String formId);
}
