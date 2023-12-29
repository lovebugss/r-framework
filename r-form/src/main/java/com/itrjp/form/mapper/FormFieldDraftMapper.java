package com.itrjp.form.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itrjp.form.pojo.entity.FormFieldDraft;
import org.apache.ibatis.annotations.Mapper;

/**
 * 表单草稿;(form_draft)表数据库访问层
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-8-14
 */
@Mapper
public interface FormFieldDraftMapper extends BaseMapper<FormFieldDraft> {
}
