package com.itrjp.form.covert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.itrjp.form.pojo.entity.*;
import com.itrjp.form.pojo.model.*;
import com.itrjp.hellocare.common.utils.JsonUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 表单类转换器
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 13:12
 */
@Mapper
public interface FormCovert {
    FormCovert INSTANCE = Mappers.getMapper(FormCovert.class);

    @Mapping(target = "formTitle", source = "title")
    @Mapping(target = "formType", source = "type")
    // formId
    //formTitle
    //collectCount
    //fieldCount
    //createTime
    @Mapping(target = "createTime", source = "createdTime")
    //createdBy
    @Mapping(target = "createdBy", source = "createdBy")
    FormItemDTO formToFormItem(Form form);

    FormConfigDTO toConfig(Form form);


    //key
    @Mapping(target = "key", source = "fieldKey")
    //id
    //label
    @Mapping(target = "type", source = "fieldType")
    @Mapping(target = "extended", ignore = true)
    FormFieldDTO toFormFieldDTO(FormField formField);

    @Mapping(target = "fieldKey", source = "key")
    @Mapping(target = "fieldType", source = "type")
    FormField toFormField(FormFieldDTO formFieldDTO);

    default String toExtendedJson(ExtendedDTO dto) {
        return dto != null ? JsonUtils.toString(dto) : null;
    }


    FieldOptionDTO toFormFieldOptionDTO(FormFieldOption formFieldOption);

    @Mapping(target = "data", expression = "java(toFormSubmitData(formFieldData))")
    @Mapping(target = "score", expression = "java(toFormScoreDTO(formData))")
    @Mapping(target = "resultId", source = "formData.uuid")
    FormResultDTO toFormResultDTO(Form form, FormData formData, List<FormFieldData> formFieldData);

    FormScoreDTO toFormScoreDTO(FormData formData);

    default Map<String, FormSubmitDataDTO> toFormSubmitData(List<FormFieldData> formFieldData) {
        return formFieldData.stream()
                .collect(Collectors.toMap(FormFieldData::getFieldKey, this::toFormSubmitDataDTO));
    }

    FormSubmitDataDTO toFormSubmitDataDTO(FormFieldData t);

    default List<FieldDataDTO> toFieldDataDTO(String data) {
        return com.itrjp.core.utils.JsonUtils.parse(data, new TypeReference<List<FieldDataDTO>>() {
        });
    }


    FormFieldOption toFormFieldOption(FieldOptionDTO fieldOptionDTO);

    FormGrade toFormGrade(FormGradeDTO formGradeDTO);
}
