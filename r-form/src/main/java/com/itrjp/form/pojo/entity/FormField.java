package com.itrjp.form.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.itrjp.form.enums.FieldType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 表单字段;
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@TableName("form_field")
@Data
public class FormField implements Serializable {
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
    /**
     * 表单key
     */
    private String fieldKey;
    /**
     * 字段类型
     */
    private FieldType fieldType;
    /**
     * 分值
     */
    private Integer score;
    /**
     * 提示信息
     */
    private String placeholder;
    /**
     * 字段名称
     */
    private String label;
    /**
     * 是否必填
     */
    private Boolean required;
    /**
     * 是否禁用
     */
    private Boolean disabled;
    /**
     * 序号
     */
    @TableField("`order`")
    private Integer order;
    private Integer max;
    private Integer min;
    /**
     * 默认值;当type为 RATE时, 值为默认分数设置
     */
    private String defaultValue;
    private String extended;

}
