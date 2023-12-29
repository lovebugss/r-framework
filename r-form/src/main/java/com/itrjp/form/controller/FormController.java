package com.itrjp.form.controller;


import com.itrjp.core.result.Result;
import com.itrjp.form.enums.FormType;
import com.itrjp.form.handler.FormHandler;
import com.itrjp.form.pojo.dto.CreateFormDTO;
import com.itrjp.form.pojo.param.CreateFormParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 */
@RestController
@RequestMapping("form")
public class FormController {

    private final Map<FormType, FormHandler> formHandlerMap;

    public FormController(List<FormHandler> formHandlerMap) {
        this.formHandlerMap = formHandlerMap
                .stream()
                .collect(Collectors.toMap(FormHandler::supportType, Function.identity()));
    }

    @PostMapping
    public Result<?> create(@RequestBody CreateFormParam param) {
        FormType type = param.getType();

        // 参数检查并转换
        FormHandler formHandler = formHandlerMap.get(type);
        // 生成唯一ID
        CreateFormDTO createFormDTO = formHandler.checkAndCovert(param);
        Object form = formHandler.createForm(createFormDTO);
        return Result.success(form);
    }
}
