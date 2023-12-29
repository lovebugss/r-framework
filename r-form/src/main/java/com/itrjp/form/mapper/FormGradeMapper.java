package com.itrjp.form.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itrjp.form.pojo.entity.FormGrade;
import com.itrjp.form.pojo.model.FormGradeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 表单评分标准;(form_grade)表数据库访问层
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@Mapper
public interface FormGradeMapper extends BaseMapper<FormGrade> {
    @Select("select * from form_grade where form_id = #{id}")
    List<FormGradeDTO> selectByFormId(String id);
}
