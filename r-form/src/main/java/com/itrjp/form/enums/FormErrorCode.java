package com.itrjp.form.enums;


import com.itrjp.core.result.ErrorCode;

/**
 * 表单异常code
 * <pre>
 *     200001~200100 表单相关
 *     200101~200200 表单字段相关
 *     200201~200300 表单结果相关
 *
 * </pre>
 *
 *
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/1 11:52
 */
public enum FormErrorCode implements ErrorCode {
    FORM_NOT_FOUND(200001, "表单不存在"),
    FORM_UN_SET_FIELD(200002, "表单未设置字段"),
    FORM_FIELD_NOT_FOUND(20003, "表单字段不存在"),
    FORM_FIELD_TYPE_NOT_FOUND(20004, "表单字段类型不存在"),
    FORM_FIELD_IS_NULL(20005, "字段为空"),
    NOT_FORM_DATA(20006, "当前用户没有提交表单"),
    FIELD_OPTION_ERROR(20007, "字段选项错误"),
    FIELD_VALUE_ERROR(20008, "字段值错误"),
    FIELD_VALUE_LIMIT_ERROR(20009, "字段值超出限制"),
    FIELD_VALUE_TYPE_ERROR(20010, "字段值类型错误"),
    OPTION_EMPTY_ERROR(20011, "选项不能为空"),
    FORM_GRADE_EMPTY(20012, "评分等级不能为空"),
    FORM_FIELD_SCORE_ERROR(20013, "字段分值不正确"),
    FORM_GRADE_LOW_ERROR(20014, "评分标准下限必须从0开始"),
    FORM_GRADE_HIGHEST_ERROR(20015, "评分标准上限不能小于分数总和"),
    // 分数档位不连续
    FORM_GRADE_NOT_CONTINUOUS_ERROR(20016, "分数档位不连续"),
    // 分数档位重叠;
    FORM_GRADE_OVERLAP_ERROR(20017, "分数档位重叠"),
    FIELD_KEY_NOT_EXITS(20018, "FieldKey: %s不存在"),
    FORM_RESULT_NOT_EXIST(20019, "表单结果不存在"),
    FIELD_OPTION_EXTRA_ERROR(20020, "附加内容不能为空"),
    INPUT_FIELD_VALUE_TO_LONG_ERROR(20021, "文本内容过长");

    private final int code;
    private final String message;

    FormErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
