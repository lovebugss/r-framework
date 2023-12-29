package com.itrjp.form.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itrjp.form.pojo.entity.FormDisease;
import org.apache.ibatis.annotations.Mapper;

/**
 * 表单病种映射;(form_disease)表数据库访问层
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-8-2
 */
@Mapper
public interface FormDiseaseMapper extends BaseMapper<FormDisease> {
}
