package com.itrjp.form.handler.form;

import com.itrjp.form.enums.FormType;
import com.itrjp.form.pojo.entity.Form;
import com.itrjp.form.pojo.entity.FormField;
import com.itrjp.form.pojo.model.*;

import java.util.List;
import java.util.Map;

/**
 * 表单处理器
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 15:27
 */
public interface FormHandler {

    /**
     * 支持的表单类型
     *
     * @return 表单类型
     * @see FormType
     */
    FormType supportType();

    /**
     * 获取表单详情
     *
     * @param form
     * @return
     */

    FormDetailDTO getFormDetail(Form form);


    /**
     * 提交表单
     * 具体提交逻辑
     * <pre>
     * 1. 检查表单是否存在
     * 2. 检查表单状态
     * 3. 检查字段必填项是否都已经填写
     * 4. 检查字段是否符合规则
     * </pre>
     *
     * @param form       表单配置
     * @param formFields 表单字段
     * @param ownerId
     * @param userId     用户ID
     * @param submitId
     * @param formData   表单数据
     * @return
     */
    SubmitFormDTO submitForm(Form form, List<FormField> formFields, String ownerId, String userId, String submitId, Map<String, List<FieldDataDTO>> formData);

    /**
     * 获取表单结果集
     *
     * @param form
     * @param userId
     * @param resultId
     * @return
     */
    FormResultDTO getFormResult(Form form, String userId, String resultId);


    /**
     * 创建表单
     *
     * @param orgCode
     * @param userId
     * @param username
     * @param config
     * @param fields
     * @return
     */
    String createForm(String orgCode, String userId, String username, FormConfigDTO config, List<FormFieldDTO> fields);


    /**
     * 修改表单信息
     *
     * @param userId
     * @param form
     * @param formFields
     * @param collect
     * @param grades
     */
    void update(String userId, Form form, List<FormField> formFields, List<FormFieldDTO> collect, List<FormGradeDTO> grades);

    void editAndFormData(Form form, String orgCode, String userId, String username, FormConfigDTO config, List<FormFieldDTO> fields);

    /**
     * 删除表单
     *
     * @param form
     * @return
     */
    boolean deleteForm(Form form);

    /**
     * 删除表单结果集
     *
     * @param form
     */
    void deleteData(Form form);

}
