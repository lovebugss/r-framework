package com.itrjp.cloud.aliyun;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.itrjp.cloud.CloudFactory;
import com.itrjp.cloud.aliyun.properties.AliyunProperties;
import com.itrjp.cloud.aliyun.storage.AliyunOSSAdapter;
import com.itrjp.cloud.storage.ObjectStorageService;

/**
 * AliyunFactory
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 16:17
 */
public class AliyunFactory implements CloudFactory {
    private final AliyunProperties properties;

    public AliyunFactory(AliyunProperties properties) {
        this.properties = properties;
    }

    @Override
    public ObjectStorageService createObjectStorage() {
        OSS ossClient = new OSSClientBuilder()
                .build(properties.getEndpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());
        return new AliyunOSSAdapter(ossClient);
    }
}
