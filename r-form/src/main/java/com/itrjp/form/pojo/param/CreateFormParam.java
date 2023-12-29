package com.itrjp.form.pojo.param;

import com.itrjp.form.enums.FormAccess;
import com.itrjp.form.enums.FormType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author r9796
 */
@Data
public class CreateFormParam {
    /**
     * 表单类型，普通表单/评分表单
     */
    @ApiModelProperty(required = true, value = "表单类型，普通表单/评分表单")
    @NotNull(message = "表单类型不能为空")
    private FormType type = FormType.NORMAL;

    /**
     * 表单访问权限
     */
    @ApiModelProperty(required = true, value = "表单权限, 公开/私有")
    @NotNull(message = "表单访问权限不能为空")
    private FormAccess access = FormAccess.PUBLIC;

    /**
     * 标题
     */
    @ApiModelProperty(required = true, value = "标题")
    @NotNull(message = "标题不能为空")
    @NotBlank(message = "标题不能为空")
    @Length(max = 50)
    private String title;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String describe;
    /**
     * 字段
     */
    @ApiModelProperty(required = true, value = "字段")
    private List<FormFieldParam> fields;
}
