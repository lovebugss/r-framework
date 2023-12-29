package com.itrjp.form.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 表单病种映射;
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-8-2
 */
@TableName("form_disease")
@Data
public class FormDisease implements Serializable {
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
     * 病种ID
     */
    private Integer diseaseId;
    private String diseaseName;

}
