package com.itrjp.form.pojo.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/17 14:59
 */
@EqualsAndHashCode(callSuper = true)
@Data

public class ScoreExtendedParam extends ExtendedParam {
    /**
     * 最大分数
     */
    private int maxScore;
}
