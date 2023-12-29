package com.itrjp.form.pojo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itrjp.form.enums.FormAccess;
import com.itrjp.form.enums.FormType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * FormConfig
 *
 * @author renjp
 */
@Data
public class FormConfigDTO {
    /**
     * 表单类型，普通表单/评分表单
     *
     * @return type
     */
    @ApiModelProperty(required = true, value = "表单类型，普通表单/评分表单")
    @NotNull(message = "表单类型不能为空")
    @JsonProperty("type")
    private FormType type;

    /**
     * 表单访问权限
     */
    @ApiModelProperty(required = true, value = "表单权限, 公开/私有")
    @NotNull(message = "表单访问权限不能为空")
    private FormAccess access;

    /**
     * 标题
     */
    @ApiModelProperty(required = true, value = "标题")
    @NotNull(message = "标题不能为空")
    @NotBlank(message = "标题不能为空")
    @JsonProperty("title")
    private String title;
    /**
     * 描述
     */
    @ApiModelProperty(required = false, value = "描述")
    @JsonProperty("describe")
    private String describe;
    /**
     * 评分标准，只有是评分表单时, 才会显示该字段
     */
    @ApiModelProperty(value = "评分标准，只有是评分表单时, 才会显示该字段")
    @JsonProperty("grades")
    @Valid
    private List<FormGradeDTO> grades;


}

