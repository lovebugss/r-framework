package com.itrjp.form.handler.field;

import com.itrjp.form.enums.FieldType;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/2 16:25
 */
public interface FieldMatcher {
    boolean support(FieldType type);

    FieldType supportType();
}
