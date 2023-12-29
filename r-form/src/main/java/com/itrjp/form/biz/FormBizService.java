package com.itrjp.form.biz;

import com.itrjp.core.result.PageResult;
import com.itrjp.form.enums.FormAccess;
import com.itrjp.form.enums.FormType;
import com.itrjp.form.pojo.entity.Form;
import com.itrjp.form.pojo.model.*;

import java.util.List;
import java.util.Map;

/**
 * - 表单;(form)表服务接口
 * - @author : <a [href="mailto:r979668507@gmail.com](mailto:href=%22mailto:r979668507@gmail.com)">renjp</a>
 * - @date: 2023-7-31
 */
public interface FormBizService {
    /**
     * 获取当前组织下公开表单, 当前用户下的私有表单
     *
     * @param orgCode
     * @param userId
     * @param type
     * @return
     */
    List<FormItemDTO> listForm(String orgCode, String userId, FormType type);

    List<Form> listForm(String orgCode, String userId);

    /**
     * 获取当前组织下所有公开表单
     *
     * @param orgCode
     * @param type
     * @return
     */
    List<FormItemDTO> listPublicFormByOrgCode(String orgCode, FormType type);

    /**
     * 获取表单详情
     */
    FormDetailDTO getFormDetail(String formId);

    /**
     * 提交表单信息
     */
    SubmitFormDTO submitForm(String formId, String ownerId, String userId, String submitId, Map<String, List<FieldDataDTO>> formData);

    /**
     * 创建表单
     */
    String createForm(String orgCode, String userId, String username, FormConfigDTO config, List<FormFieldDTO> fields);

    /**
     * 删除表单
     * <pre>
     *  1. 删除表单配置信息
     *  2. 删除表单数据
     *  3. 删除表单字段
     *  4. 删除表单字段信息
     *  5. 删除表单草稿
     *  6. 删除表单选项
     *  7. 删除表单评分
     *  8. 删除表单映射信息(病种, 科室, 用户)
     * </pre>
     */
    boolean deleteForm(String userId, String formId);


    FormResultDTO getFormResult(String formId, String userId, String resultId);

    /**
     * 获取表单结果集
     *
     * @param submitterId
     * @param userId
     * @param doctorId
     * @param sort
     * @return
     */
    List<FormDataDTO> getFormResultByUserIds(String submitterId, List<String> userId, List<String> doctorId, String sort);

    PageResult<List<FormDTO>> pageFormList(Integer page, Integer pageSize,
                                           String keyword,
                                           String sortKey,
                                           String sortType,
                                           Integer diseaseId,
                                           Integer deptId,
                                           FormAccess access,
                                           String orgCode,
                                           String userId);


    /**
     * 更新表单字段
     *
     * @param userId
     * @param formId
     * @param collect
     * @param grades
     */
    void updateFormFields(String userId, String formId, List<FormFieldDTO> collect, List<FormGradeDTO> grades);

    void editAndFormData(String formId, String orgCode, String userId, String username, FormConfigDTO config, List<FormFieldDTO> fields);


}
