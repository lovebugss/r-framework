package com.itrjp.form.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 表单科室映射;
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-8-2
 */
@TableName("form_department")
@Data
public class FormDepartment implements Serializable {
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
     * 科室ID
     */
    private Integer deptId;
    private String deptName;

}
