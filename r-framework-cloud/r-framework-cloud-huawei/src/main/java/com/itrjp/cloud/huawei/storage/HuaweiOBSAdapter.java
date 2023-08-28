package com.itrjp.cloud.huawei.storage;

import com.itrjp.cloud.huawei.HuaweiService;
import com.itrjp.cloud.huawei.properties.HuaweiProperties;
import com.itrjp.cloud.storage.ObjectStorageService;
import com.obs.services.ObsClient;

import java.io.File;

public class HuaweiOBSAdapter extends HuaweiService implements ObjectStorageService {

    private final ObsClient obsClient;

    public HuaweiOBSAdapter(HuaweiProperties properties) {
        this.obsClient = new ObsClient(properties.getAccessKeyId(), properties.getAccessKeySecret(), properties.getEndpoint());
    }

    @Override
    public void uploadFile(String bucket, String file) {

    }

    @Override
    public void uploadFile(String bucket, File file) {

    }

    @Override
    public boolean has(String bucket, String key) {
        return false;
    }

    @Override
    public void headObject(String bucket, String key) {

    }

    @Override
    public void getAccessToken() {

    }
}
