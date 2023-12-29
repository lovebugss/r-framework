package com.itrjp.form.pojo.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/17 14:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageExtendedParam extends ExtendedParam {
    /**
     * 图片最大数量
     */
    private int limit;
}
