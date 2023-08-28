package com.itrjp.cloud.huawei;


import com.itrjp.cloud.CloudService;
import com.itrjp.cloud.consts.CloudType;

public abstract class HuaweiService implements CloudService {
    @Override
    public CloudType getCloudType() {
        return CloudType.Huawei;
    }
}
