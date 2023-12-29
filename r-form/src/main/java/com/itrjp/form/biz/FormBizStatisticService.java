package com.itrjp.form.biz;

import com.itrjp.form.pojo.model.FormStatisticDTO;

import java.util.List;
import java.util.Map;

/**
 * 表单统计服务
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 14:09
 */
public interface FormBizStatisticService {
    /**
     * 表单统计
     *
     * @param formIds
     * @return
     */
    List<FormStatisticDTO> formStatisticByFormIds(List<String> formIds);

    /**
     * 统计已经收集的表单数量
     *
     * @param formId
     * @return
     */
    Long collectCountByFormId(String formId);

    Long collectCountByDoctorId(String formId);

    Map<String, Integer> formSubmitCountByUserIds(List<String> patientIds);
}
