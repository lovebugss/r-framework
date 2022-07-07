package com.itrjp.cloud.aws;

import com.itrjp.cloud.base.CloudService;
import com.itrjp.cloud.consts.CloudType;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 16:20
 */
public abstract class AmazonService implements CloudService {
    @Override
    public CloudType getCloudType() {
        return CloudType.Amazon;
    }
}
