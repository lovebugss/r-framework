package com.itrjp.form.handler;

import com.itrjp.form.enums.FormType;
import com.itrjp.form.pojo.dto.CreateFormDTO;
import com.itrjp.form.pojo.param.CreateFormParam;

public interface FormHandler {
    FormType supportType();

    Object createForm(CreateFormDTO param);

    CreateFormDTO checkAndCovert(CreateFormParam param);
}
