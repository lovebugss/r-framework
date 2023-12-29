package com.itrjp.form.pojo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FieldOptionParam {
    private String fieldKey;
    /**
     * 名称
     */
    @ApiModelProperty(required = true, value = "名称")
    @NotNull(message = "选项名称不能为空")
    @Length(max = 50, message = "选项名称不能超过50个字符")
    private String label;

    /**
     * 分值
     */
    @ApiModelProperty(required = true, value = "分值")
    private Integer score;
    /**
     * 补充信息
     */
    @ApiModelProperty(value = "补充信息")
    private Boolean replenish;
    /**
     * 值
     */
    @ApiModelProperty(required = true, value = "值")
    @NotNull(message = "选项值不能为空")
    @NotBlank(message = "选项值不能为空")
    private String value;

}
