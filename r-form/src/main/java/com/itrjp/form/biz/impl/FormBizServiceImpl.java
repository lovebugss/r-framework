package com.itrjp.form.biz.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itrjp.core.result.PageResult;
import com.itrjp.form.biz.FormBizService;
import com.itrjp.form.biz.FormBizStatisticService;
import com.itrjp.form.covert.FormCovert;
import com.itrjp.form.enums.FormAccess;
import com.itrjp.form.enums.FormType;
import com.itrjp.form.exception.FormNotFoundException;
import com.itrjp.form.exception.FormUnSetFieldException;
import com.itrjp.form.handler.form.FormHandler;
import com.itrjp.form.mapper.FormMapper;
import com.itrjp.form.pojo.entity.Form;
import com.itrjp.form.pojo.entity.FormField;
import com.itrjp.form.pojo.model.*;
import com.itrjp.form.service.FormDataService;
import com.itrjp.form.service.FormFieldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.itrjp.core.consts.StrConst.EMPTY;

/**
 * 表单业务service
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@Service
@Slf4j
public class FormBizServiceImpl implements FormBizService {
    private final FormMapper formMapper;
    private final FormFieldService formFieldService;
    private final FormDataService formDataService;
    private final FormBizStatisticService formBizStatisticService;
    private final Map<FormType, FormHandler> formHandlerMap;

    public FormBizServiceImpl(FormMapper formMapper, FormFieldService formFieldService, FormDataService formDataService, FormBizStatisticService formBizStatisticService, ObjectProvider<FormHandler> formHandlerList) {
        this.formMapper = formMapper;
        this.formFieldService = formFieldService;
        this.formDataService = formDataService;
        this.formBizStatisticService = formBizStatisticService;
        this.formHandlerMap = formHandlerList
                .stream()
                .collect(Collectors.toMap(FormHandler::supportType, Function.identity()));
    }


    @Override
    @Transactional
    public String createForm(String orgCode, String userId, String username, FormConfigDTO config, List<FormFieldDTO> fields) {
        // 根据表单类型, 选择不同的处理器进行创建
        FormHandler formHandler = formHandlerMap.get(config.getType());
        // 创建表单
        return formHandler.createForm(orgCode, userId, username, config, fields);
    }

    @Override
    public void editAndFormData(String formId, String orgCode, String userId, String username, FormConfigDTO config, List<FormFieldDTO> fields) {
        Form form = formMapper.findById(formId)
                .orElseThrow(FormNotFoundException::new);
        formHandlerMap.get(config.getType())
                .editAndFormData(form, orgCode, userId, username, config, fields);
    }

    @Override
    public SubmitFormDTO submitForm(String formId, String ownerId, String userId, String submitId, Map<String, List<FieldDataDTO>> formData) {
        log.info("提交表单, formId:{}, userId:{}, formData:{}", formId, userId, formData);
        // 检查表单是否存在
        Form form = formMapper.findById(formId)
                .orElseThrow(FormNotFoundException::new);
        // 查询表单字段
        List<FormField> formFields = formFieldService.findByFormId(form.getFormId())
                .orElseThrow(FormUnSetFieldException::new);

        return formHandlerMap.get(form.getType())
                .submitForm(form, formFields, ownerId, userId, submitId, formData);
    }

    @Override
    public List<FormItemDTO> listForm(String orgCode, String userId, FormType type) {
        List<Form> forms = formMapper.selectFormList(orgCode, userId, type.name());

        if (forms.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> formIds = forms.stream().map(Form::getFormId).collect(Collectors.toList());
        // 表单统计
        List<FormStatisticDTO> formStatisticDtoList = formBizStatisticService.formStatisticByFormIds(formIds);
        return packageResult(forms, formStatisticDtoList);
    }

    @Override
    public List<Form> listForm(String orgCode, String userId) {

        return formMapper.selectFormList(orgCode, userId, null);
    }

    @Override
    public List<FormItemDTO> listPublicFormByOrgCode(String orgCode, FormType type) {
        List<Form> forms = formMapper.selectPublicFormByOrgCode(orgCode, type);
        if (forms.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> formIds = forms.stream().map(Form::getFormId).collect(Collectors.toList());
        // 表单统计
        List<FormStatisticDTO> formStatisticDtoList = formBizStatisticService.formStatisticByFormIds(formIds);
        return packageResult(forms, formStatisticDtoList);
    }

    private List<FormItemDTO> packageResult(List<Form> forms, List<FormStatisticDTO> formStatisticDtoS) {
        Map<String, FormStatisticDTO> statisticMap = formStatisticDtoS.stream().collect(Collectors.toMap(FormStatisticDTO::getFormId, Function.identity()));
        List<FormItemDTO> result = new ArrayList<>();
        for (Form form : forms) {
            FormItemDTO item = FormCovert.INSTANCE.formToFormItem(form);

            // 统计信息
            FormStatisticDTO orDefault = statisticMap.getOrDefault(form.getFormId(), new FormStatisticDTO());
            item.setCollectCount(orDefault.getCollectCount());
            item.setFieldCount(orDefault.getFieldCount());
            result.add(item);
        }
        return result;
    }

    @Override
    public FormDetailDTO getFormDetail(String formId) {
        log.info("获取表单详情, formId:{}", formId);
        // 表单配置
        Form form = formMapper.findById(formId).orElseThrow(FormNotFoundException::new);
        return formHandlerMap.get(form.getType()).getFormDetail(form);

    }

    @Override
    public FormResultDTO getFormResult(String formId, String userId, String resultId) {
        Form form = formMapper.findById(formId).orElseThrow(FormNotFoundException::new);
        return formHandlerMap.get(form.getType()).getFormResult(form, userId, resultId);
    }

    @Override
    public boolean deleteForm(String userId, String formId) {
        Form form = formMapper.findBy(userId, formId)
                .orElseThrow(FormNotFoundException::new);
        return formHandlerMap.get(form.getType())
                .deleteForm(form);
    }

    @Override
    public List<FormDataDTO> getFormResultByUserIds(String submitterId, List<String> userId, List<String> doctorId, String sort) {
        return formDataService.getFormResultByUserIds(submitterId, userId, doctorId, sort);
    }

    @Override
    public PageResult<List<FormDTO>> pageFormList(Integer currentPage, Integer pageSize, String keyword, String sortKey, String sortType, Integer diseaseId, Integer deptId, FormAccess access, String orgCode, String userId) {

        //
        IPage<FormDTO> page = new Page<>(currentPage, pageSize);

        IPage<FormDTO> pageResult = this.formMapper.pageFormList(page, keyword, access != null ? access.name() : EMPTY, orgCode, deptId, diseaseId, userId, sortKey, sortType);

        return PageResult.success(pageResult.getRecords(), currentPage, pageSize, pageResult.getTotal());
    }

    @Override
    public void updateFormFields(String userId, String formId, List<FormFieldDTO> collect, List<FormGradeDTO> grades) {
        Form form = formMapper.findById(formId)
                .orElseThrow(FormNotFoundException::new);
        // 检查表单是否为自己创建
        if (!Objects.equals(userId, form.getCreatedBy())) {
            throw new FormNotFoundException();
        }
        // 查询表单字段
        List<FormField> formFields = formFieldService.findByFormId(form.getFormId())
                .orElseThrow(FormUnSetFieldException::new);
        formHandlerMap.get(form.getType())
                .update(userId, form, formFields, collect, grades);
    }
}
