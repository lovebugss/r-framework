package com.itrjp.form.pojo.model;

import com.itrjp.form.enums.FormAccess;
import lombok.Data;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/14 18:33
 */
@Data
public class FormDataDTO {
    /**
     * 表单ID
     */
    private String formId;
    private FormAccess formAccess;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 数据
     */
    /**
     * 得分
     */
    private Integer score;
    /**
     * 评分等级
     */
    private String level;

    private String submitTime;
    private String submitterId;
    private String title;
    private String resultId;
    private String ownerId;
}
