package com.itrjp.form.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.itrjp.form.enums.FormAccess;
import com.itrjp.form.enums.FormType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 表单;
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@TableName("form")
@Data
public class Form implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String orgCode;
    private String formId;
    private String userId;
    /**
     * 唯一ID
     */
    private String uuid;
    /**
     * 表单名称
     */
    private String title;
    /**
     * 描述
     */
    @TableField("`describe`")
    private String describe;
    /**
     * 表单类型;普通表单, 评分表单
     */
    private FormType type;
    /**
     * 是否公开
     */
    private FormAccess access;
    /**
     * 状态
     */
    private String status;
    private Integer fieldCount;
    private Integer collectCount;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
private String createdBy;
    private String createdByName;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
private String updatedBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

}
