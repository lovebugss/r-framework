package com.itrjp.form.pojo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * FormSubmitData
 */
@Data
public class FormSubmitDataDTO {
    /**
     * key
     *
     * @return fieldKey
     */
    @ApiModelProperty(required = true, value = "key")
    @NotNull
    @JsonProperty("fieldKey")
    private String fieldKey;


    /**
     * Get data
     *
     * @return data
     */
    @ApiModelProperty(required = true)
    @NotNull
    @JsonProperty("data")
    private List<FieldDataDTO> data;

}

