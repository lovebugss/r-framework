package com.itrjp.cloud.aws.storage;

import com.itrjp.cloud.aws.AmazonService;
import com.itrjp.cloud.base.storage.ObjectStorageService;
import com.itrjp.cloud.properties.AmazonProperties;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;

import java.io.File;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 15:55
 */
@Service
public class AmazonS3Adapter extends AmazonService implements ObjectStorageService {

    private final S3Client s3Client;
    private final AmazonProperties properties;

    public AmazonS3Adapter(S3Client s3Client, AmazonProperties properties) {
        this.s3Client = s3Client;
        this.properties = properties;
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
        HeadObjectRequest headObjectRequest = HeadObjectRequest.builder().bucket(bucket).key(key).build();
        HeadObjectResponse response = s3Client.headObject(headObjectRequest);
        response.eTag();
    }

}
