package com.itrjp.cloud.aliyun;

import com.itrjp.cloud.base.CloudService;
import com.itrjp.cloud.consts.CloudType;

/**
 * AliyunService
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 16:20
 */
public abstract class AliyunService implements CloudService {
    @Override
    public CloudType getCloudType() {
        return CloudType.Aliyun;
    }
}
