package com.itrjp.form.pojo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * FieldOption
 *
 * @author renjp
 */
@Data
public class FieldOptionDTO {
    private Integer id;
    /**
     * 名称
     */
    @ApiModelProperty(required = true, value = "名称")
    @NotNull(message = "选项名称不能为空")
    @JsonProperty("label")
    @Length(max = 50, message = "选项名称不能超过50个字符")
    private String label;


    private String fieldKey;
    /**
     * 分值
     */
    @ApiModelProperty(required = true, value = "分值")
//    @NotNull(message = "分值不能为空")
    @JsonProperty("score")
    private Integer score;
    /**
     * 补充信息
     */
    @ApiModelProperty(value = "补充信息")
    @JsonProperty("replenish")
    private Boolean replenish;
    /**
     * 值
     */
    @ApiModelProperty(required = true, value = "值")
    @NotNull(message = "选项值不能为空")
    @NotBlank(message = "选项值不能为空")
    @JsonProperty("value")
    private String value;

}

