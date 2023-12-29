package com.itrjp.form.biz.impl;

import com.itrjp.form.biz.FormBizStatisticService;
import com.itrjp.form.mapper.FormMapper;
import com.itrjp.form.pojo.model.FormDataDTO;
import com.itrjp.form.pojo.model.FormStatisticDTO;
import com.itrjp.form.service.FormDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 14:51
 */
@Service
@RequiredArgsConstructor
public class FormBizStatisticServiceImpl implements FormBizStatisticService {
    private final FormMapper formMapper;
    private final FormDataService formDataService;

    @Override
    public List<FormStatisticDTO> formStatisticByFormIds(List<String> formIds) {
        if (formIds != null && !formIds.isEmpty()) {
            return formMapper.formStatisticByFormIds(formIds);
        }
        return Collections.emptyList();
    }

    @Override
    public Long collectCountByFormId(String formId) {
        return formDataService.countByFormId(formId);
    }

    @Override
    public Long collectCountByDoctorId(String formId) {
        return formDataService.countByOwnerId(formId);
    }

    @Override
    public Map<String, Integer> formSubmitCountByUserIds(List<String> patientIds) {
        if (patientIds != null && !patientIds.isEmpty()) {
            List<FormDataDTO> formDataDTOs = this.formDataService.formSubmitCountByUserIds(patientIds);
            return formDataDTOs.stream().collect(Collectors.groupingBy(FormDataDTO::getUserId, Collectors.summingInt(a -> 1)));
        }
        return Collections.emptyMap();
    }
}
