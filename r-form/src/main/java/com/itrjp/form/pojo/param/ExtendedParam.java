package com.itrjp.form.pojo.param;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * @author r9796
 */
@Data
@JsonSubTypes({
        @JsonSubTypes.Type(value = ImageExtendedParam.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = InputExtendedParam.class, name = "INPUT"),
        @JsonSubTypes.Type(value = ScoreExtendedParam.class, name = "SCORE")
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "fieldType", include = JsonTypeInfo.As.PROPERTY)
public class ExtendedParam {
}
