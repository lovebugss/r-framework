package com.itrjp.cloud.huawei;

import com.itrjp.cloud.CloudFactory;
import com.itrjp.cloud.huawei.properties.HuaweiProperties;
import com.itrjp.cloud.huawei.storage.HuaweiOBSAdapter;
import com.itrjp.cloud.storage.ObjectStorageService;

public class HuaweiFactory implements CloudFactory {
    private final HuaweiProperties properties;

    public HuaweiFactory(HuaweiProperties properties) {
        this.properties = properties;
    }

    @Override
    public ObjectStorageService createObjectStorage() {

        return new HuaweiOBSAdapter(properties);
    }
}
