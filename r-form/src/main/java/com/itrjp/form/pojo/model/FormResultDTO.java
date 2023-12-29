package com.itrjp.form.pojo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/3 18:03
 */
@Data
public class FormResultDTO {
    /**
     * 表单数据(注意, fieldKey不是有序)
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

    private String resultId;
}
