package com.itrjp.form.pojo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * FormScore
 */
@Data
public class FormScoreDTO {
    /**
     * 最终得分
     *
     * @return score
     */
    @ApiModelProperty(required = true, value = "最终得分")
    @NotNull
    @JsonProperty("score")
    private Integer score;

    /**
     * 结论
     *
     * @return summary
     */
    @ApiModelProperty(required = true, value = "结论")
    @NotNull
    @JsonProperty("summary")
    private String summary;
    private FormGradeDTO grade;

}

