package com.itrjp.form.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itrjp.form.pojo.entity.FormFieldOption;
import org.apache.ibatis.annotations.Mapper;

/**
 * 表单字段选项;(form_field_option)表数据库访问层
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@Mapper
public interface FormFieldOptionMapper extends BaseMapper<FormFieldOption> {
}
