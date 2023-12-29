package com.itrjp.form.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itrjp.form.enums.FormErrorCode;
import com.itrjp.form.exception.FormException;
import com.itrjp.form.mapper.FormDataMapper;
import com.itrjp.form.pojo.entity.FormData;
import com.itrjp.form.pojo.model.FormDataDTO;
import com.itrjp.form.pojo.model.FormSummitLog;
import com.itrjp.form.pojo.model.OwnerCountDTO;
import com.itrjp.form.service.FormDataService;
import com.itrjp.hellocare.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户表单数据;(form_data)表服务实现类
 *
 * @author : <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date : 2023-7-31
 */
@Service
public class FormDataServiceImpl extends ServiceImpl<FormDataMapper, FormData> implements FormDataService {
    @Override
    public List<FormSummitLog> getSubmitFormListByPatientId(String doctorId, String patientId) {
        if (StringUtils.isBlank(patientId)) {
            return Collections.emptyList();
        }
        return this.baseMapper.getSubmitFormListByPatientId(doctorId, patientId);
    }


    @Override
    public Long countByFormId(String formId) {
        LambdaQueryWrapper<FormData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FormData::getFormId, formId);
        return this.baseMapper.selectCount(queryWrapper);
    }

    @Override
    public Long countByOwnerId(String ownerId) {
        LambdaQueryWrapper<FormData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FormData::getOwnerId, ownerId);
        return this.baseMapper.selectCount(queryWrapper);
    }

    @Override
    public List<FormDataDTO> getFormResultByUserIds(String submitterId, List<String> userId, List<String> doctorId, String sort) {
        if (userId == null || userId.isEmpty()) {
            return Collections.emptyList();
        }
        return this.baseMapper.getFormResultByUserIds(submitterId, userId, doctorId, sort);
    }

    @Override
    public FormDataDTO getByResultId(String resultId) {
        if (StringUtils.isBlank(resultId)) {
            return null;
        }
        FormDataDTO byResultId = this.baseMapper.getByResultId(resultId);
        if (byResultId == null) {
            throw new FormException(FormErrorCode.FORM_RESULT_NOT_EXIST);
        }
        return byResultId;
    }

    @Override
    public void deleteByFormId(String formId) {
        this.baseMapper.delete(new LambdaQueryWrapper<FormData>().eq(FormData::getFormId, formId));
    }

    @Override
    public IPage<FormDataDTO> getFormResult(Integer currentPage, Integer pageSize, List<String> ownerIds, List<String> patientIds, List<String> formIds, String startTime, String endTime, String sort) {
        IPage<FormDataDTO> page = new Page<>(currentPage, pageSize);
        LocalDateTime now = LocalDateTime.now();
        if (StringUtils.isBlank(startTime)) {
            // 当前时间 -7d
            startTime = DateUtils.format(now.plusDays(-7));
        }
        if (StringUtils.isBlank(endTime)) {
            // 当前时间
            endTime = DateUtils.format(now);
        }
        // 这里将ownerIds 传入null. 表示不需要关联医生信息
        return this.baseMapper.getFormResult(page, ownerIds, patientIds, formIds, startTime + " 00:00:00", endTime + " 23:59:59", sort.toUpperCase());
    }

    @Override
    public List<FormDataDTO> formSubmitCountByUserIds(List<String> patientIds) {

        if (patientIds == null || patientIds.isEmpty()) {
            return Collections.emptyList();
        }
        return this.baseMapper.formSubmitCountByUserIds(patientIds);
    }

    @Override
    public String getLastSubmitUserIdByPatientId(String userId, String patientId) {

        return this.baseMapper.formSubmitCountByPatientId(userId, patientId);
    }

    @Override
    public Map<String, Integer> getOwnerFormCount(List<String> doctorIds) {
        if (doctorIds == null || doctorIds.isEmpty()) {
            return Collections.emptyMap();

        }
        List<OwnerCountDTO> ownerFormCount = this.baseMapper.getOwnerFormCount(doctorIds);
        return ownerFormCount.stream().collect(Collectors.toMap(OwnerCountDTO::getOwnerId, OwnerCountDTO::getCount));
    }
}
