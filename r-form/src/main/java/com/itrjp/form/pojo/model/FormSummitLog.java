package com.itrjp.form.pojo.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/3 17:17
 */
@Data
public class FormSummitLog {
    private String title;
    private String formId;
    private LocalDateTime submitTime;
    private String resultId;
}
