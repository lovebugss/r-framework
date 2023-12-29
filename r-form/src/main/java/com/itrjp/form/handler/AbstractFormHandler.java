package com.itrjp.form.handler;

import com.itrjp.form.pojo.dto.CreateFormDTO;

import java.util.UUID;

public abstract class AbstractFormHandler implements FormHandler {
    @Override
    public Object createForm(CreateFormDTO param) {
        String formId = UUID.randomUUID().toString().replace("-", "");
        save(formId, param);
        return null;
    }

    private void save(String formId, CreateFormDTO dto) {

        saveFormConfig(formId);
        saveFormField(formId);
        saveFormOption(formId);
    }

    private void saveFormOption(String formId) {

    }

    private void saveFormField(String formId) {

    }

    private void saveFormConfig(String formId) {

    }
}
