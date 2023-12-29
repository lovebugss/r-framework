package com.itrjp.form.handler.field;

import cn.hutool.core.collection.CollUtil;
import com.itrjp.core.exception.BaseException;
import com.itrjp.form.enums.FormErrorCode;
import com.itrjp.form.enums.FormType;
import com.itrjp.form.exception.FormException;
import com.itrjp.form.pojo.entity.FormField;
import com.itrjp.form.pojo.model.FieldDataDTO;
import com.itrjp.form.pojo.model.FieldOptionDTO;
import com.itrjp.form.pojo.model.FormConfigDTO;
import com.itrjp.form.pojo.model.FormFieldDTO;
import com.itrjp.form.service.FormFieldOptionService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Option 类型字段通用检查
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/3 09:42
 */
public abstract class OptionFieldHandler extends AbstractFormFieldHandler {
    private final FormFieldOptionService formFieldOptionService;

    protected OptionFieldHandler(FormFieldOptionService formFieldOptionService) {
        this.formFieldOptionService = formFieldOptionService;
    }

    @Override
    public FormFieldDTO getFormField(FormField formField) {
        FormFieldDTO dto = super.getFormField(formField);
        // 查询option
        dto.setOption(formFieldOptionService.getOptionByFieldKey(formField.getFieldKey()));
        return dto;
    }

    @Override
    public void checkFieldValue(FormFieldDTO formField, List<FieldDataDTO> fieldData) {
        super.checkFieldValue(formField, fieldData);

        // 检查option, 这里只需要处理value 不为null 的情况. 因为若字段必填, null的情况在父类中已经处理了
        if (CollUtil.isNotEmpty(fieldData)) {
            List<FieldOptionDTO> option = formField.getOption();
            // option 根据value 分组
            Map<String, FieldOptionDTO> optionMap = option.stream().collect(Collectors.toMap(FieldOptionDTO::getValue, Function.identity()));

            // 检查value 是否在option中
            for (FieldDataDTO val : fieldData) {
                // 检查当前值是否在选项内
                if (optionMap.containsKey(val.getValue())) {
                    FieldOptionDTO optionDTO = optionMap.get(val.getValue());
                    // 检查是否有附加信息
                    if (Boolean.TRUE.equals(optionDTO.getReplenish()) && StringUtils.isBlank(val.getExtra())) {
                        throw new BaseException(FormErrorCode.FIELD_OPTION_EXTRA_ERROR);
                    }
                } else {
                    throw new BaseException(FormErrorCode.FIELD_OPTION_ERROR);
                }
            }
        }
    }

    @Override
    public int calculateScore(FormFieldDTO formField, final List<FieldDataDTO> value) {
        // 查找选项
        List<FieldOptionDTO> option = formField.getOption();
        List<String> valList = value.stream().map(FieldDataDTO::getValue).collect(Collectors.toList());
        // 过滤出来已有选项, 然后分值求和
        return option.stream()
                .filter(o -> valList.contains(o.getValue()))
                .mapToInt(FieldOptionDTO::getScore).sum();

    }

    @Override
    public void checkAndSetField(String formId, FormConfigDTO config, FormFieldDTO formFieldDTO) {
        super.checkAndSetField(formId, config, formFieldDTO);
        // 检查option
        List<FieldOptionDTO> option = formFieldDTO.getOption();
        if (option == null || option.isEmpty()) {
            throw new FormException(FormErrorCode.OPTION_EMPTY_ERROR);
        }
        //
        for (FieldOptionDTO optionDTO : option) {
            optionDTO.setFieldKey(formFieldDTO.getKey());
            if (FormType.SCORE.equals(config.getType())) {
                if (isScoreError(optionDTO.getScore())) {
                    throw new FormException(FormErrorCode.FORM_FIELD_SCORE_ERROR);
                }
            } else {
                // 重置分数
                optionDTO.setScore(null);
            }
        }
    }
}
