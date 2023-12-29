package com.itrjp.form.handler.form;

import com.itrjp.form.enums.FormType;
import com.itrjp.form.handler.field.FormFieldHandler;
import com.itrjp.form.mapper.FormMapper;
import com.itrjp.form.pojo.entity.Form;
import com.itrjp.form.service.FormDataService;
import com.itrjp.form.service.FormFieldDataService;
import com.itrjp.form.service.FormFieldOptionService;
import com.itrjp.form.service.FormFieldService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 普通表单处理器
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 18:02
 */
@Service
public class NormalFormHandler extends AbstractFormHandler {

    public NormalFormHandler(FormMapper formService, FormFieldService formFieldMapper, List<FormFieldHandler> formFieldHandlers, FormDataService formDataMapper, FormFieldDataService fieldDataService, FormFieldOptionService formFieldOptionService) {
        super(formService, formFieldMapper, formFieldHandlers, formDataMapper, fieldDataService, formFieldOptionService);
    }

    @Override
    public FormType supportType() {
        return FormType.NORMAL;
    }

    @Override
    public void deleteData(Form form) {

    }
}
