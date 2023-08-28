package com.itrjp.cloud.aliyun.storage;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;
import com.itrjp.cloud.aliyun.AliyunService;
import com.itrjp.cloud.storage.ObjectStorageService;

import java.io.File;

/**
 * TODO 阿里云对象存储实现
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 16:08
 */
public class AliyunOSSAdapter extends AliyunService implements ObjectStorageService {
    private final OSS client;

    public AliyunOSSAdapter(OSS ossClient) {
        this.client = ossClient;
    }

    @Override
    public void uploadFile(String bucket, String file) {
        UploadFileRequest request = new UploadFileRequest(bucket, file);
        try {
            UploadFileResult result = client.uploadFile(request);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void uploadFile(String bucket, File file) {

    }

    @Override
    public boolean has(String bucket, String key) {
        headObject(bucket, key);
        return false;
    }

    @Override
    public void headObject(String bucket, String key) {
        ObjectMetadata objectMetadata = client.headObject(bucket, key);
    }

    @Override
    public void getAccessToken() {

    }
}
