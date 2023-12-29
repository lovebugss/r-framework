package com.itrjp.form.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 表单评分标准;
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@TableName("form_grade")
@Data
public class FormGrade implements Serializable {
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
     * 档位
     */
    private Integer level;
    /**
     * 下线
     */
    private Integer lowest;
    /**
     * 上线
     */
    private Integer highest;
    /**
     * 结论
     */
    private String summary;

}
