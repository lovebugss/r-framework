package com.itrjp.form.pojo.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class FieldDataDTO {
    private String value;
    @Length(max = 20)
    private String extra;
}
