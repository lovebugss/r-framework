package com.itrjp.form.pojo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itrjp.form.enums.FormAccess;
import com.itrjp.form.enums.FormType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/1 11:18
 */
@Data
public class FormItemDTO {
    /**
     * 表单ID
     *
     * @return formId
     */
    @ApiModelProperty(required = true, value = "表单ID")
    @NotNull
    @JsonProperty("formId")
    private String formId;

    /**
     * 标题
     *
     * @return formTitle
     */
    @ApiModelProperty(required = true, value = "标题")
    @NotNull
    @JsonProperty("formTitle")
    private String formTitle;


    /**
     * 表单类型
     *
     * @return formType
     */
    @ApiModelProperty(required = true, value = "表单类型")
    @NotNull
    @JsonProperty("formType")
    private FormType formType;

    /**
     * 收集数量
     *
     * @return collectCount
     */
    @ApiModelProperty(required = true, value = "收集数量")
    @NotNull
    @JsonProperty("collectCount")
    private Integer collectCount;

    /**
     * 题目数量
     *
     * @return fieldCount
     */
    @ApiModelProperty(required = true, value = "题目数量")
    @NotNull
    @JsonProperty("fieldCount")
    private Integer fieldCount;

    /**
     * 访问权限, 公开/私有
     */
    @ApiModelProperty(required = true, value = "访问权限, 公开/私有")
    @NotNull
    @JsonProperty("access")
    private FormAccess access;


    /**
     * 创建时间
     *
     * @return createTime
     */
    @ApiModelProperty(required = true, value = "创建时间")
    @NotNull
    @JsonProperty("createTime")
    private String createTime;
    /**
     * 创建人
     */
    @ApiModelProperty(required = true, value = "创建人")
    @NotNull
    @JsonProperty("createdBy")
    private String createdBy;
    private String createdByName;

}
