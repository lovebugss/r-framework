package com.itrjp.form.pojo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/17 14:59
 */
@EqualsAndHashCode(callSuper = true)
@Data

public class ScoreExtendedDTO extends ExtendedDTO {
    /**
     * 最大分数
     */
    private int maxScore;
}
