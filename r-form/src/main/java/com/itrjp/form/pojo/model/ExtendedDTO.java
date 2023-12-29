package com.itrjp.form.pojo.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/17 14:59
 */
@JsonSubTypes({
        @JsonSubTypes.Type(value = ImageExtendedDTO.class, name = "IMAGE"),
        @JsonSubTypes.Type(value = InputExtendedDTO.class, name = "INPUT"),
        @JsonSubTypes.Type(value = ScoreExtendedDTO.class, name = "SCORE")
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "fieldType", include = JsonTypeInfo.As.PROPERTY)
@Data
public class ExtendedDTO {
}
