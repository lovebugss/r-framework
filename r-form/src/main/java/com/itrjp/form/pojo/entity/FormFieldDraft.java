package com.itrjp.form.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 表单草稿;
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-8-14
 */
@TableName("form_field_draft")
@Data
public class FormFieldDraft implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 表单ID
     */
    private String formId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 表单字段Key
     */
    private String fieldKey;
    /**
     * 表单字段value
     */
    private String fieldValue;

}
