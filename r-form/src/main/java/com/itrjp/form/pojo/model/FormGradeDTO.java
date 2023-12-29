package com.itrjp.form.pojo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class FormGradeDTO {
    /**
     * 等级
     *
     * @return level
     */
    @ApiModelProperty(required = true, value = "等级")
    @NotNull(message = "档位不能为空")
    @JsonProperty("level")
    @Min(1)
    private Integer level;

    /**
     * 下限
     *
     * @return lowest
     */
    @ApiModelProperty(required = true, value = "下限")
    @NotNull(message = "分数下线不能为空")
    @JsonProperty("lowest")
    @Min(value = 0, message = "分数下线不能小于0")
    private Integer lowest;

    /**
     * 上限
     *
     * @return highest
     */
    @ApiModelProperty(required = true, value = "上限")
    @NotNull(message = "分数上线不能为空")
    @JsonProperty("highest")
    @Min(value = 1, message = "分数上线不能小于1")
    private Integer highest;

    /**
     * 结论
     *
     * @return summary
     */
    @ApiModelProperty(required = true, value = "结论")
    @NotNull(message = "结论不能为空")
    @JsonProperty("summary")
    @NotBlank(message = "结论不能为空")
    private String summary;


}

