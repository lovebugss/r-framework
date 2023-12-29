package com.itrjp.form.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表单数据;
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@TableName("form_data")
@Data
public class FormData implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 唯一ID
     */
    private String uuid;
    /**
     * 表单ID
     */
    private String formId;
    /**
     * 表单填写人
     */
    private String userId;
    /**
     * 表单归属人ID
     */
    private String ownerId;
    /**
     * 提交人ID
     */
    private String submitterId;
    /**
     * 数据
     */
    /**
     * 得分
     */
    private Integer score;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 评分等级
     */
    private String level;


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

}
