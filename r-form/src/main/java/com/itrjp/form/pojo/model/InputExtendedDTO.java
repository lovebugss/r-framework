package com.itrjp.form.pojo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/17 14:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InputExtendedDTO extends ExtendedDTO {
    /**
     * 多行输入
     */
    private boolean multiline;
}
