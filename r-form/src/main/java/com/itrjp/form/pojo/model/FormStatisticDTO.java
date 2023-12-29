package com.itrjp.form.pojo.model;

import lombok.Data;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 14:39
 */
@Data
public class FormStatisticDTO {
    private String formId;

    /**
     * 表单字段数量
     */
    private Integer fieldCount;

    /**
     * 已收集的表单数量
     */
    private Integer collectCount;

}
