package com.itrjp.form.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 表单字段选项;
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@TableName("form_field_option")
@Data
public class FormFieldOption implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
private String createdBy;
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
    /**
     * 表单ID
     */
    private String formId;
    private String fieldKey;
    /**
     * 表单值
     */
    @TableField("`value`")
    private String value;
    /**
     * 排序
     */
    @TableField("`order`")
    private Integer order;
    /**
     * 补充信息
     */
    private Boolean replenish;
    /**
     * 名称
     */
    private String label;
    /**
     * 分值
     */
    private Integer score;

}
