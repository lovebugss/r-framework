package com.itrjp.form.handler.form;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itrjp.core.exception.BaseException;
import com.itrjp.core.utils.JsonUtils;
import com.itrjp.core.utils.UUIDUtils;
import com.itrjp.form.covert.FormCovert;
import com.itrjp.form.enums.FieldType;
import com.itrjp.form.enums.FormErrorCode;
import com.itrjp.form.exception.FieldKeyNotExitsException;
import com.itrjp.form.handler.field.FormFieldHandler;
import com.itrjp.form.mapper.FormMapper;
import com.itrjp.form.pojo.entity.*;
import com.itrjp.form.pojo.model.*;
import com.itrjp.form.service.FormDataService;
import com.itrjp.form.service.FormFieldDataService;
import com.itrjp.form.service.FormFieldOptionService;
import com.itrjp.form.service.FormFieldService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 抽象表单处理器. 抽取普通表单与评分表单公共部分, 便于后续扩展
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 15:29
 */
@Slf4j
public abstract class AbstractFormHandler implements FormHandler {

    protected final FormMapper formMapper;
    protected final FormFieldService formFieldService;
    protected final Map<FieldType, FormFieldHandler> formFieldHandlerMap;
    protected final FormDataService formDataService;
    protected final FormFieldDataService fieldDataService;
    protected final FormFieldOptionService formFieldOptionService;

    protected AbstractFormHandler(FormMapper formService, FormFieldService formFieldService, List<FormFieldHandler> formFieldHandlers, FormDataService formDataService, FormFieldDataService fieldDataService, FormFieldOptionService formFieldOptionService) {
        this.formMapper = formService;
        this.formFieldService = formFieldService;
        this.formDataService = formDataService;
        this.fieldDataService = fieldDataService;
        formFieldHandlerMap = formFieldHandlers.stream().collect(Collectors.toMap(FormFieldHandler::supportType, Function.identity()));
        this.formFieldOptionService = formFieldOptionService;
    }


    @Override
    public FormDetailDTO getFormDetail(Form form) {
        String formId = form.getFormId();

        FormDetailDTO result = new FormDetailDTO();
        // 表单配置

        result.setConfig(FormCovert.INSTANCE.toConfig(form));

        // 获取表单字段
        Optional<List<FormField>> byFormId = formFieldService.findByFormId(formId);
        if (byFormId.isPresent()) {
            List<FormField> formFields = byFormId.get();
            List<com.itrjp.form.pojo.model.FormFieldDTO> formFieldDTODtos = new ArrayList<>();
            for (FormField formField : formFields) {
                FieldType fieldType = formField.getFieldType();
                log.info("表单字段类型:{}", fieldType);
                formFieldDTODtos.add(formFieldHandlerMap.get(fieldType).getFormField(formField));
            }
            formFieldDTODtos.sort(Comparator.comparingInt(com.itrjp.form.pojo.model.FormFieldDTO::getOrder));
            // 设置表单字段
            result.setFields(formFieldDTODtos);
        }
        return result;
    }


    @Override
    public String createForm(String orgCode, String userId, String username, FormConfigDTO config, List<FormFieldDTO> fields) {

        // 创建表单ID
        String formId = UUIDUtils.getUUID();
        checkForm(userId, formId, config, fields);
        // 保存数据
        save(orgCode, formId, userId, username, config, fields);
        return formId;
    }

    private void save(String orgCode, String formId, String userId, String username, FormConfigDTO config, List<FormFieldDTO> fields) {
        // 保存表单配置
        saveFormConfig(orgCode, formId, userId, username, config, fields);
        // 保存表单字段
        saveFormField(formId, userId, fields);
        // 保存表单字段选项
        saveFormFieldOption(formId, userId, fields);
        // 保存评分等级
        saveFormGrade(formId, userId, config.getGrades());
    }

    @Override
    public void editAndFormData(Form form, String orgCode, String userId, String username, FormConfigDTO config, List<FormFieldDTO> fields) {
        String formId = form.getFormId();

        checkForm(userId, formId, config, fields);
        // 保存数据
        deleteForm(form);
        deleteData(form);
        save(orgCode, formId, userId, username, config, fields);
    }

    @Override
    public void deleteData(Form form) {
        formDataService.deleteByFormId(form.getFormId());
        fieldDataService.deleteByFormId(form.getFormId());
    }

    @Override
    public boolean deleteForm(Form form) {
        //
        String formId = form.getFormId();
        formMapper.delete(new LambdaQueryWrapper<Form>().eq(Form::getFormId, formId));
        formFieldService.deleteByFormId(formId);
        formFieldOptionService.deleteByFormId(formId);
        formDataService.deleteByFormId(formId);
        fieldDataService.deleteByFormId(formId);
        return true;
    }

    /**
     * 钩子函数
     *
     * @param formId
     * @param userId
     * @param grades
     */
    protected void saveFormGrade(String formId, String userId, List<FormGradeDTO> grades) {
    }

    private void saveFormFieldOption(String formId, String userId, List<FormFieldDTO> fields) {
        List<FormFieldOption> collect = fields.stream()
                .map(FormFieldDTO::getOption)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                // 过滤掉垃圾数据
                .filter(option -> option.getFieldKey() != null).map(o -> {
                    FormFieldOption option = FormCovert.INSTANCE.toFormFieldOption(o);
                    option.setCreatedBy(userId);
                    option.setCreatedTime(LocalDateTime.now());
                    option.setFormId(formId);
                    return option;
                }).collect(Collectors.toList());
        formFieldOptionService.saveBatch(collect);


    }

    private void saveFormField(String formId, String userId, List<FormFieldDTO> fields) {
        List<FormField> collect = fields.stream().map(f -> {
            FormField formField = FormCovert.INSTANCE.toFormField(f);
            // 设置表单ID
            formField.setFormId(formId);
            formField.setCreatedBy(userId);
            formField.setCreatedTime(LocalDateTime.now());
            return formField;
        }).collect(Collectors.toList());
        formFieldService.saveBatch(collect);

    }

    private void saveFormConfig(String orgCode, String formId, String userId, String username, FormConfigDTO config, List<FormFieldDTO> fields) {
        Form form = new Form();
        form.setFormId(formId);
        form.setOrgCode(orgCode);
        form.setType(config.getType());
        form.setAccess(config.getAccess());
        form.setTitle(config.getTitle());
        form.setDescribe(config.getDescribe());
        form.setUserId(userId);
        form.setCreatedBy(userId);
        form.setCreatedByName(username);
        form.setCreatedTime(LocalDateTime.now());
        form.setFieldCount(fields.size());
        formMapper.insert(form);
    }

    /**
     * 表单检查
     * 1. 检查表单字段是否完整, 合法.
     * 2. 检查表单字段是否有重复
     * 3. 检查表单字段是否超过最多限制
     *
     * @param userId
     * @param formId
     * @param config
     * @param fields
     */
    private void checkForm(String userId, String formId, FormConfigDTO config, List<FormFieldDTO> fields) {
        // 检查
        for (FormFieldDTO field : fields) {
            FieldType type = field.getType();
            formFieldHandlerMap.get(type).checkAndSetField(formId, config, field);
            checkFormFieldHook(config, field);
        }

        // 钩子函数, 其他检查
        checkFormHook(config, fields);
    }

    protected void checkFormFieldHook(FormConfigDTO config, FormFieldDTO field) {

    }

    protected void checkFormHook(FormConfigDTO config, List<FormFieldDTO> fields) {
    }


    @Override
    public SubmitFormDTO submitForm(Form form, List<FormField> formFields, String ownerId, String userId, String submitId, Map<String, List<FieldDataDTO>> fieldData) {

        // 将表单字段转成dto. 便于后续处理, dto中会增加一些字段, 如: option, extended.
        List<FormFieldDTO> formFieldDtos = formFields.stream().map(formField -> formFieldHandlerMap.get(formField.getFieldType()).getFormField(formField)).collect(Collectors.toList());
        // 检查表单字段
        for (FormFieldDTO formField : formFieldDtos) {
            formFieldHandlerMap.get(formField.getType()).checkFieldValue(formField, fieldData.get(formField.getKey()));
        }
        // 计算结果
        FormScoreDTO result = calculationResult(form, formFieldDtos, fieldData);
        // 保存
        String resultId = UUIDUtils.getUUID();
        save(ownerId, userId, submitId, form, fieldData, result, resultId);
        return wrapperResult(form, formFieldDtos, fieldData, result, resultId);
    }

    protected SubmitFormDTO wrapperResult(Form form, List<FormFieldDTO> formFields, Map<String, List<FieldDataDTO>> fieldData, FormScoreDTO result, String resultId) {
        SubmitFormDTO.SubmitFormDTOBuilder success = SubmitFormDTO.builder().success(true).resultId(resultId);
        if (result != null) {
            success.score(result.getScore())
                    .summary(result.getSummary())
                    .grade(result.getGrade());
        }
        return success.build();
    }

    /**
     * 计算结果
     *
     * @param form
     * @param formFields
     * @param fieldData
     */
    protected FormScoreDTO calculationResult(Form form, List<FormFieldDTO> formFields, Map<String, List<FieldDataDTO>> fieldData) {
        return null;
    }

    private void save(String ownerId, String userId, String submitId, Form form, Map<String, List<FieldDataDTO>> fieldData, FormScoreDTO result, String resultId) {
        // 生成一个唯一id

        saveFormFieldData(resultId, userId, form, fieldData);
        saveFormData(resultId, ownerId, userId, submitId, form, fieldData, result);
    }

    private void saveFormFieldData(String resultId, String userId, Form form, Map<String, List<FieldDataDTO>> fieldData) {
        fieldDataService.saveBatch(fieldData.entrySet()
                .stream()
                // 过滤掉值为空的
                .filter(entry -> entry.getValue() != null)
                .map(entry -> {
                    FormFieldData formFieldData = new FormFieldData();
                    formFieldData.setFormId(form.getFormId());
                    formFieldData.setData(JsonUtils.toJsonString(entry.getValue()));
                    formFieldData.setFieldKey(entry.getKey());
                    formFieldData.setUserId(userId);
                    formFieldData.setUuid(resultId);
                    return formFieldData;
                }).collect(Collectors.toList()));
    }

    protected void saveFormData(String uuid, String ownerId, String userId, String submitId, Form form, Map<String, List<FieldDataDTO>> fieldData, FormScoreDTO result) {
        // 批量保存表单数据
        FormData formData = new FormData();
        // 获取当前登录的用户信息
        formData.setFormId(form.getFormId());
        formData.setUserId(userId);
        formData.setCreatedTime(LocalDateTime.now());
        formData.setCreatedBy(userId);
        formData.setOwnerId(ownerId);
        formData.setSubmitterId(submitId);
        formData.setUuid(uuid);
        formDataService.save(formData);
    }


    @Override
    public FormResultDTO getFormResult(Form form, String userId, String resultId) {
        log.info("查询表单结果, formId:{}, userId:{}, resultId:{}", form.getFormId(), userId, resultId);
        LambdaQueryWrapper<FormData> eq = new LambdaQueryWrapper<FormData>().eq(FormData::getFormId, form.getFormId()).eq(FormData::getUserId, userId);
        if (resultId != null) {
            eq.eq(FormData::getUuid, resultId);
        }
        FormData formData = formDataService.getOne(eq);
        if (formData == null) {
            throw new BaseException(FormErrorCode.NOT_FORM_DATA);
        }
        LambdaQueryWrapper<FormFieldData> eq1 = new LambdaQueryWrapper<FormFieldData>()
                .eq(FormFieldData::getFormId, form.getFormId())
                .eq(FormFieldData::getUuid, resultId);
        List<FormFieldData> formFieldData = fieldDataService.list(eq1);
        return FormCovert.INSTANCE.toFormResultDTO(form, formData, formFieldData);
    }

    /**
     * 修改表单
     * <pre>
     * 注意: 只能修改文案(标题, 提示信息, 选项), 或者新增题目, 新增选项
     *       如果是评分表单, 只能修改结论, 或者新增评分等级
     * </pre>
     *
     * @param userId
     * @param form
     * @param allFields
     * @param collect
     * @param grades
     */
    @Override
    public void update(String userId, Form form, List<FormField> allFields, List<FormFieldDTO> collect, List<FormGradeDTO> grades) {
        // 将表单字段转成dto. 便于后续处理, dto中会增加一些字段, 如: option, extended.
        List<FormFieldDTO> formFieldDTOs = allFields
                .stream()
                .map(formField -> formFieldHandlerMap.get(formField.getFieldType()).getFormField(formField))
                .collect(Collectors.toList());
        // 将字段分组, 分成设置 FieldKey 的和没有设置 FieldKey 的
        Map<Boolean, List<FormFieldDTO>> fieldGroup = collect
                .stream()
                .collect(Collectors.groupingBy(f -> StringUtils.isBlank(f.getKey())));
        // 如果是新增字段, 需要对字段进行检查
        updateCheck();

        // 如果是修改字段, 需要检查当前字段是否存在, 如果存在, 则修改
        List<FormFieldDTO> toUpdateFields = new ArrayList<>();
        List<FormFieldDTO> toAddFields = new ArrayList<>();
        // 所有字段, 用了校验分值
        List<FormFieldDTO> fields = new ArrayList<>();
        for (FormFieldDTO fieldDTO : collect) {
            if (StringUtils.isBlank(fieldDTO.getKey())) {
                toAddFields.add(fieldDTO);
            } else {
                toUpdateFields.add(fieldDTO);
            }
            fields.add(fieldDTO);
        }


        // todo 参数校验
        // 设置field 的值
        // 待更新的key
        List<FormFieldDTO> todoFields = fieldGroup.get(false);
        if (todoFields != null && !todoFields.isEmpty()) {
            // 检查key 是否存在
            checkFieldKeyExist(todoFields, formFieldDTOs);
            // 保存数据
            updateField(userId, form, todoFields, formFieldDTOs, allFields.stream().collect(Collectors.toMap(FormField::getFieldKey, FormField::getId)));

        }

        // 没有设置fieldKey 的值
        List<FormFieldDTO> addFields = fieldGroup.get(true);
        if (addFields != null && !addFields.isEmpty()) {
            // 保存数据
            addFormFields(userId, form, addFields);
        }

        // 保存评分等级
        updateFormGrade(form.getFormId(), userId, grades);

    }

    private void updateCheck() {

        // 跟新检查
        // 检查
    }

    /**
     * todo, 放在这里不太合适, 因为只有评分表单才需要这个方法
     *
     * @param formId
     * @param userId
     * @param grades
     */
    protected void updateFormGrade(String formId, String userId, List<FormGradeDTO> grades) {
    }

    private void addFormFields(String userId, Form form, List<FormFieldDTO> addFields) {
        // 生成fieldKey
        for (FormFieldDTO addField : addFields) {
            addField.setKey(UUIDUtils.generateShortUuid());
            // 设置option
            List<FieldOptionDTO> option = addField.getOption();
            if (option != null && !option.isEmpty()) {
                for (FieldOptionDTO fieldOptionDTO : addField.getOption()) {
                    fieldOptionDTO.setFieldKey(addField.getKey());
                }
            }

        }
        saveFormField(form.getFormId(), userId, addFields);
        // 更新表单字段数
        Form updateForm = new Form();
        updateForm.setId(form.getId());
        updateForm.setFieldCount(form.getFieldCount() + addFields.size());
        formMapper.updateById(updateForm);
        // 保存字段选项
        saveFormFieldOption(form.getFormId(), userId, addFields);
    }

    private void updateField(String userId, Form form, List<FormFieldDTO> fields, List<FormFieldDTO> allFields, Map<String, Integer> fieldIdMap) {

        List<FormField> collect = fields.stream()
                .map(f -> {
                    FormField formField = FormCovert.INSTANCE.toFormField(f);
                    // 设置表单ID
                    formField.setFormId(form.getFormId());
                    formField.setUpdatedBy(userId);
                    formField.setUpdatedTime(LocalDateTime.now());
                    formField.setId(fieldIdMap.get(f.getKey()));
                    return formField;
                }).collect(Collectors.toList());
        formFieldService.updateBatchById(collect);

        // 对已经保存的数据, 根据fieldKey 进行分组
        final Map<String, Map<String, FieldOptionDTO>> collect1 = new HashMap<>();
        for (FormFieldDTO allField : allFields) {
            List<FieldOptionDTO> option = allField.getOption();
            if (option == null || option.isEmpty()) {
                continue;
            }
            option.forEach(o -> {
                Map<String, FieldOptionDTO> optionMap = collect1.computeIfAbsent(o.getFieldKey(), k -> new HashMap<>());
                optionMap.put(o.getValue(), o);
            });
        }
        // 待更新的集合
        // 更新option
        List<FormFieldOption> toUpdateOptions = fields.stream()
                // 过滤掉空数据
                .filter(field -> field.getOption() != null && !field.getOption().isEmpty())
                .map(formFieldDTO -> {
                    String key = formFieldDTO.getKey();
                    List<FieldOptionDTO> option = formFieldDTO.getOption();

                    // 设置ID (fieldKey 相同, value 相同)
                    List<FormFieldOption> result = new ArrayList<>();
                    Map<String, FieldOptionDTO> optionMap = collect1.get(key);
                    for (FieldOptionDTO optionDTO : option) {
                        FieldOptionDTO fieldOptionDTO = optionMap.get(optionDTO.getValue());
                        FormFieldOption formFieldOption = new FormFieldOption();
                        if (fieldOptionDTO != null) {
                            // 存在, 需要修改
                            formFieldOption.setId(fieldOptionDTO.getId());
                            // 设置审计
                            formFieldOption.setUpdatedBy(userId);
                            formFieldOption.setUpdatedTime(LocalDateTime.now());
                        } else {
                            // 新增
                            formFieldOption.setCreatedBy(userId);
                            formFieldOption.setCreatedTime(LocalDateTime.now());
                        }
                        formFieldOption.setFieldKey(key);
                        formFieldOption.setValue(optionDTO.getValue());
                        formFieldOption.setFormId(form.getFormId());
                        formFieldOption.setScore(optionDTO.getScore());
                        formFieldOption.setReplenish(optionDTO.getReplenish());
                        formFieldOption.setLabel(optionDTO.getLabel());
                        result.add(formFieldOption);
                    }
                    return result;
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // 批量更新
        formFieldOptionService.saveOrUpdateBatch(toUpdateOptions);
    }

    /**
     * 检查key 是否存在
     *
     * @param todoFields 待更新的field
     * @param allFields  已经存在的field
     */
    private void checkFieldKeyExist(List<FormFieldDTO> todoFields, List<FormFieldDTO> allFields) {
        // 检查key 是否存在
        Set<String> keys = allFields.stream().map(FormFieldDTO::getKey).collect(Collectors.toSet());
        for (FormFieldDTO formFieldDTO : todoFields) {
            if (!keys.contains(formFieldDTO.getKey())) {
                throw new FieldKeyNotExitsException(formFieldDTO.getKey());
            }

        }
    }


}
