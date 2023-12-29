package com.itrjp.form.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表单草稿;
 * 注意: 用户的草稿信息放在患者端, 是因为只有患者端才有这个功能, 如果放到通用表单模块, 业务不太通用, 会增加复杂度. 后续可以考虑在表单系统中增加一个草稿功能
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-8-14
 */
@TableName("form_draft")
@Data
public class FormDraft implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 就诊人ID
     */
    private String patientId;
    /**
     * 表单ID
     */
    private String formId;

    private Integer fieldNumber;


}
