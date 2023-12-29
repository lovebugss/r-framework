package com.itrjp.form.pojo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/17 14:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageExtendedDTO extends ExtendedDTO {
    /**
     * 图片最大数量
     */
    private int limit;
}
