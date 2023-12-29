package com.itrjp.form.pojo.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itrjp.form.enums.FieldType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
public class FormFieldParam {
    /**
     * Get key
     */
    @ApiModelProperty(required = false, value = "fieldKey, 新增时请忽略")
    @JsonProperty("key")
    private String key;


    /**
     * 标签
     */
    @ApiModelProperty(required = true, value = "标签")
    @NotNull(message = "字段名称不能为空")
    @JsonProperty("label")
    @NotBlank(message = "字段名称不能为空")
    @Length(max = 50, message = "字段名称不能超过50个字符")
    private String label;


    /**
     * 字段类型，SELECT/INPUT ....
     */
    @ApiModelProperty(required = true, value = "字段类型，SELECT/INPUT ....")
    @NotNull(message = "字段类型不能为空")
    @JsonProperty("type")
    private FieldType type;
    /**
     * 仅当type为select/rate时出现
     */
    @ApiModelProperty(value = "仅当type为select/rate时出现")
    @JsonProperty("option")
    @Valid
    @Size(max = 10, message = "一个选项最多能创建20个选项，当前已达到上限，无法继续创建")
    private List<FieldOptionParam> option;

    private boolean required;
    /**
     * 是否禁用
     */
    private boolean disabled;
    /**
     * 分值
     */
    private Double score;
    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    private String placeholder;

    /**
     * 序号
     */
    private Integer order;
    @Max(value = 11)
    private Integer max;
    @Min(value = 0)
    private Integer min;
    /**
     * 默认值;当type为 RATE时, 值为默认分数设置
     */
    private String defaultValue;
    /**
     * 拓展属性, 主要用来保存不同字段的额外信息, json格式
     */
    @ApiModelProperty(value = "拓展属性, 主要用来保存不同字段的额外信息, json格式. 若设置extended, 需要指定FieldType(必须)", example = "{\"fieldType\":\"INPUT\",\"multiline\":true}")
    private ExtendedParam extended;
}
