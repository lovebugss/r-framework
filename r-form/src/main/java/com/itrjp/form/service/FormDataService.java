package com.itrjp.form.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itrjp.form.pojo.entity.FormData;
import com.itrjp.form.pojo.model.FormDataDTO;
import com.itrjp.form.pojo.model.FormSummitLog;

import java.util.List;
import java.util.Map;

/**
 * 用户表单数据;(form_data)表服务接口
 *
 * @author : <a [href="mailto:r979668507@gmail.com](mailto:href=%22mailto:r979668507@gmail.com)">renjp</a>
 * @date: 2023-7-31
 */
public interface FormDataService extends IService<FormData> {

    List<FormSummitLog> getSubmitFormListByPatientId(String userId, String patientId);


    /**
     * 根据表单ID统计已经收集的表单数量
     *
     * @param formId
     * @return
     */
    Long countByFormId(String formId);

    /**
     * 根据创建这ID统计已经收集的表单数量
     *
     * @param ownerId
     * @return
     */
    Long countByOwnerId(String ownerId);

    List<FormDataDTO> getFormResultByUserIds(String submitterId, List<String> userId, List<String> doctorId, String sort);

    FormDataDTO getByResultId(String resultId);

    void deleteByFormId(String formId);

    /**
     * 统计已经收集的表单数量
     *
     * @param page
     * @param pageSize
     * @param ownerIds
     * @param patientIds
     * @param formIds
     * @param startTime
     * @param endTime
     * @param sort
     * @return
     */
    IPage<FormDataDTO> getFormResult(Integer page, Integer pageSize, List<String> ownerIds, List<String> patientIds, List<String> formIds, String startTime, String endTime, String sort);

    List<FormDataDTO> formSubmitCountByUserIds(List<String> patientIds);

    /**
     * 查询最后一次提交人userId
     *
     * @param userId
     * @param patientId
     * @return
     */
    String getLastSubmitUserIdByPatientId(String userId, String patientId);

    Map<String, Integer> getOwnerFormCount(List<String> doctorIds);
}
