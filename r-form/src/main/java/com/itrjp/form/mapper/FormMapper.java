package com.itrjp.form.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itrjp.form.enums.FormType;
import com.itrjp.form.pojo.entity.Form;
import com.itrjp.form.pojo.model.FormDTO;
import com.itrjp.form.pojo.model.FormStatisticDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 表单;(form)表数据库访问层
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@Mapper
public interface FormMapper extends BaseMapper<Form> {
    Optional<Form> findById(@Param("formId") String formId);

    Optional<Form> findBy(@Param("userId") String userId, @Param("formId") String formId);

    List<Form> selectByUserId(@Param("userId") String userId, @Param("type") FormType type);

    List<Form> selectByOpenId(@Param("openId") String openId, @Param("type") FormType type);

    List<Form> selectPublicFormByOrgCode(@Param("orgCode") String orgCode, @Param("type") FormType type);

    List<FormStatisticDTO> formStatisticByFormIds(@Param("list") List<String> formIds);

    IPage<FormDTO> pageFormList(IPage<FormDTO> page,
                                @Param("keyword") String keyword,
                                @Param("access") String access,
                                @Param("orgCode") String orgCode,
                                @Param("deptId") Integer deptId,
                                @Param("diseaseId") Integer diseaseId,
                                @Param("userId") String userId,
                                @Param("sortKey") String sortKey,
                                @Param("sortType") String sortType);

    void addFormCollectCount(@Param("formId") String formId);

    List<Form> selectFormList(@Param("orgCode") String orgCode, @Param("userId") String userId, @Param("type") String type);
}
