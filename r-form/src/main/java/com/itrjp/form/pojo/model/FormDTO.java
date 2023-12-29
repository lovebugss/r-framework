package com.itrjp.form.pojo.model;

import com.itrjp.form.enums.FormAccess;
import com.itrjp.form.enums.FormType;
import lombok.Data;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/16 10:51
 */
@Data
public class FormDTO {

    private String formId;
    private String title;
    private FormType type;
    private FormAccess access;
    private String disease;
    private String dept;
    private String orgCode;
    private String createdBy;
    private String createdTime;
    private String createdByName;

}
