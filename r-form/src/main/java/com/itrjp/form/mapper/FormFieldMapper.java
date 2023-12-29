package com.itrjp.form.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itrjp.form.pojo.entity.FormField;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 表单字段;(form_field)表数据库访问层
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@Mapper
public interface FormFieldMapper extends BaseMapper<FormField> {
    @Select("select * from form_field where form_id = #{formId}")
    List<FormField> findByFormId(@Param("formId") String formId);
}
