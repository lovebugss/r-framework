package com.itrjp.form.handler.form;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itrjp.form.covert.FormCovert;
import com.itrjp.form.enums.FormErrorCode;
import com.itrjp.form.enums.FormType;
import com.itrjp.form.exception.FormException;
import com.itrjp.form.handler.field.FormFieldHandler;
import com.itrjp.form.mapper.FormMapper;
import com.itrjp.form.pojo.entity.Form;
import com.itrjp.form.pojo.entity.FormData;
import com.itrjp.form.pojo.entity.FormFieldOption;
import com.itrjp.form.pojo.entity.FormGrade;
import com.itrjp.form.pojo.model.*;
import com.itrjp.form.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 评分表单处理器
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 15:30
 */
@Service
public class ScoreFormHandler extends AbstractFormHandler {

    private final FormGradeService formGradeService;

    public ScoreFormHandler(FormMapper formService, FormFieldService formFieldMapper, FormGradeService formGradeService, List<FormFieldHandler> formFieldHandlers, FormDataService formDataMapper, FormFieldDataService formFieldDataMapper, FormFieldOptionService formFieldOptionService) {
        super(formService, formFieldMapper, formFieldHandlers, formDataMapper, formFieldDataMapper, formFieldOptionService);
        this.formGradeService = formGradeService;
    }

    @Override
    public FormDetailDTO getFormDetail(Form form) {
        FormDetailDTO formDetail = super.getFormDetail(form);
        // 查询评分等级
        List<FormGradeDTO> grades = formGradeService.selectByFormId(form.getFormId());
        formDetail.getConfig().setGrades(grades);
        return formDetail;
    }

    @Override
    protected FormScoreDTO calculationResult(Form form, List<FormFieldDTO> formFields, Map<String, List<FieldDataDTO>> fieldData) {
        FormScoreDTO formScoreDTO = new FormScoreDTO();
        // 计算分值,
        Integer score = statisticalScore(form, formFields, fieldData);
        // 根据分支, 查找对应等级
        List<FormGradeDTO> grades = formGradeService.selectByFormId(form.getFormId());

        FormGradeDTO grade = findGrade(grades, score);
        formScoreDTO.setScore(score);
        formScoreDTO.setSummary(grade.getSummary());
        formScoreDTO.setGrade(grade);
        return formScoreDTO;
    }

    /**
     * 统计最终得分
     *
     * @param form
     * @param formFields 表单字段
     * @param fieldData
     * @return
     */
    private Integer statisticalScore(Form form, List<FormFieldDTO> formFields, Map<String, List<FieldDataDTO>> fieldData) {
        if (fieldData.isEmpty()) {
            return 0;
        }
        // 循环答案, 判断是否正确, 统计得分
        return formFields
                .stream()
                .mapToInt(field -> this.statistical(fieldData, field))
                .sum();
    }

    private int statistical(Map<String, List<FieldDataDTO>> entity, FormFieldDTO formField) {
        String fieldKey = formField.getKey();
        if (entity.containsKey(fieldKey)) {
            return formFieldHandlerMap.get(formField.getType()).calculateScore(formField, entity.get(fieldKey));
        }
        return 0;
    }

    private FormGradeDTO findGrade(List<FormGradeDTO> grades, Integer score) {
        return grades.stream().filter(g -> g.getLowest() <= score && g.getHighest() > score)
                .findFirst()
                .orElse(new FormGradeDTO());

    }

    @Override
    protected void checkFormHook(FormConfigDTO config, List<FormFieldDTO> fields) {
        // 因为是评分表单, 检查是否有评分标准
        List<FormGradeDTO> grades = config.getGrades();
        if (grades == null || grades.isEmpty()) {
            throw new FormException(FormErrorCode.FORM_GRADE_EMPTY);
        }

        checkGrades(fields, grades);
    }

    /**
     * <pre>
     * 检查评分等级是否合法
     * 1. 判断分数上限是否超过表单内总和
     * 2. 判断档位是否有重叠
     * 3. 判断档位是否有缺失
     * </pre>
     *
     * @param fields
     * @param grades
     */
    private void checkGrades(List<FormFieldDTO> fields, List<FormGradeDTO> grades) {
        // 对等级进行排序
        grades.sort(Comparator.comparingInt(FormGradeDTO::getLevel));
        // 获取表单总分
        int sum = fields.stream().mapToInt(field -> {
            List<FieldOptionDTO> option = field.getOption();
            return option == null ? field.getScore() : option.stream().mapToInt(FieldOptionDTO::getScore).sum();

        }).sum();
        // 检查分数上线是否超过表单总分
        int max = grades.stream().mapToInt(FormGradeDTO::getHighest).max().orElse(0);
        if (max < sum) {
            throw new FormException(FormErrorCode.FORM_GRADE_HIGHEST_ERROR);
        }
        // 检查档位是否有重叠
        for (int i = 0; i < grades.size() - 1; i++) {
            FormGradeDTO current = grades.get(i);
            FormGradeDTO next = grades.get(i + 1);
            if (current.getHighest() > next.getLowest()) {
                throw new FormException(FormErrorCode.FORM_GRADE_OVERLAP_ERROR);
            }
        }
        // 检查是否有缺失
        for (int i = 0; i < grades.size() - 1; i++) {
            FormGradeDTO current = grades.get(i);
            FormGradeDTO next = grades.get(i + 1);
            if (current.getHighest() + 1 != next.getLowest()) {
                throw new FormException(FormErrorCode.FORM_GRADE_NOT_CONTINUOUS_ERROR);
            }
        }
        // 检查下线是否从0开始
        if (grades.get(0).getLowest() != 0) {
            throw new FormException(FormErrorCode.FORM_GRADE_LOW_ERROR);
        }

    }

    @Override
    protected void saveFormData(String uuid, String ownerId, String userId, String submitId, Form form, Map<String, List<FieldDataDTO>> fieldData, FormScoreDTO result) {
        // 批量保存表单数据
        FormData formData = new FormData();
        // 获取当前登录的用户信息
        formData.setFormId(form.getFormId());
        formData.setUserId(userId);
        formData.setSubmitterId(submitId);
        formData.setCreatedTime(LocalDateTime.now());
        formData.setCreatedBy(userId);
        formData.setOwnerId(ownerId);
        formData.setScore(result.getScore());
        formData.setSummary(result.getSummary());
        formData.setUuid(uuid);
        formDataService.save(formData);
    }

    @Override
    protected void saveFormGrade(String formId, String userId, List<FormGradeDTO> grades) {
        formGradeService.saveBatch(grades.stream().map(a -> {
                            FormGrade formGrade = FormCovert.INSTANCE.toFormGrade(a);
                            formGrade.setCreatedBy(userId);
                            formGrade.setFormId(formId);
                            formGrade.setCreatedTime(LocalDateTime.now());
                            return formGrade;
                        }
                )
                .collect(Collectors.toList()));
    }

    @Override
    protected void updateFormGrade(String formId, String userId, List<FormGradeDTO> grades) {

        // 查询当前表单的所有评分, 并根据level进行分组
        Map<Integer, FormGradeDTO> gradeDTOMap = formGradeService.selectByFormId(formId)
                .stream()
                .collect(Collectors.toMap(FormGradeDTO::getLevel, Function.identity()));
        // 合并数据

        // TODO 检查数据是否合法

        // 保存数据
        // TODO 可以改成批量操作
        for (FormGradeDTO grade : grades) {
            Integer level = grade.getLevel();
            if (gradeDTOMap.containsKey(level)) {
                FormGrade updateEntity = new FormGrade();
                updateEntity.setSummary(grade.getSummary());
                updateEntity.setUpdatedBy(userId);
                updateEntity.setUpdatedTime(LocalDateTime.now());
                // 执行修改
                formGradeService.update(updateEntity, new LambdaQueryWrapper<FormGrade>().eq(FormGrade::getFormId, formId).eq(FormGrade::getLevel, level));
            } else {
                // 执行新增
                FormGrade insertEntity = new FormGrade();
                insertEntity.setFormId(formId);
                insertEntity.setSummary(grade.getSummary());
                insertEntity.setCreatedBy(userId);
                insertEntity.setCreatedTime(LocalDateTime.now());
                formGradeService.save(insertEntity);
            }
        }
    }

    @Override
    public FormType supportType() {
        return FormType.SCORE;
    }

    @Override
    public void deleteData(Form form) {
        formGradeService.remove(new LambdaQueryWrapper<FormGrade>().eq(FormGrade::getFormId, form.getFormId()));
        formFieldOptionService.remove(new LambdaQueryWrapper<FormFieldOption>().eq(FormFieldOption::getFormId, form.getFormId()));
    }

    @Override
    public boolean deleteForm(Form form) {
        formGradeService.deleteByFormId(form.getFormId());
        return super.deleteForm(form);
    }
}
