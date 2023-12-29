package com.itrjp.form.pojo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * FormDetail
 *
 * @author renjp
 */
@Data
public class FormDetailDTO {
    @JsonProperty("config")
    @ApiModelProperty(required = true, value = "")
    @NotNull
    @Valid
    private FormConfigDTO config;


    /**
     * 表单字段
     *
     * @return fields
     */
    @ApiModelProperty(required = true, value = "表单字段")
    @NotNull
    @JsonProperty("fields")
    @Valid
    private List<FormFieldDTO> fields;

    /**
     * 表单数据
     */
    @JsonProperty("data")
    private Map<String, FormSubmitDataDTO> data;

    /**
     * 用户得分，分数, 仅评分表单才会有该字段
     *
     * @return score
     */
    @ApiModelProperty(value = "用户得分，分数, 仅评分表单才会有该字段")
    @JsonProperty("score")
    @Valid
    private FormScoreDTO score;
}

