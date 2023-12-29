package com.itrjp.form.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itrjp.form.pojo.entity.FormData;
import com.itrjp.form.pojo.model.FormDataDTO;
import com.itrjp.form.pojo.model.FormSummitLog;
import com.itrjp.form.pojo.model.OwnerCountDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表单数据;(form_data)表数据库访问层
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@Mapper
public interface FormDataMapper extends BaseMapper<FormData> {
    List<FormSummitLog> getSubmitFormListByPatientId(@Param("ownerId") String doctorId, @Param("userId") String patientId);


    List<FormDataDTO> getFormResultByUserIds(@Param("submitterId") String submitterId, @Param("userIds") List<String> userIds, @Param("ownerIds") List<String> ownerIds, @Param("sortType") String sort);

    FormDataDTO getByResultId(String resultId);

    IPage<FormDataDTO> getFormResult(IPage<FormDataDTO> page,
                                     @Param("ownerIds") List<String> ownerIds,
                                     @Param("userIds") List<String> userId,
                                     @Param("formIds") List<String> formIds,
                                     @Param("startTime") String startTime,
                                     @Param("endTime") String endTime, @Param("sortType") String sort);

    List<FormDataDTO> formSubmitCountByUserIds(@Param("userIds") List<String> patientIds);

    String formSubmitCountByPatientId(@Param("ownerId") String userId, @Param("userId") String patientId);

    List<OwnerCountDTO> getOwnerFormCount(@Param("ownerIds")List<String> ownerIds);
}
