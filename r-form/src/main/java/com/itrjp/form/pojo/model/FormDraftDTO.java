package com.itrjp.form.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/14 17:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormDraftDTO {
    private Map<String, List<FieldDataDTO>> data;
}
