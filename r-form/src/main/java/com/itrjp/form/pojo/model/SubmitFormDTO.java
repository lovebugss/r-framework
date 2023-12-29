package com.itrjp.form.pojo.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 17:32
 */
@Data
@Builder
public class SubmitFormDTO {
    private boolean success;
    private String resultId;
    private Integer score;
    private String summary;
    private FormGradeDTO grade;
}
