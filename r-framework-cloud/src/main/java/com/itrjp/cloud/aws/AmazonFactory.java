package com.itrjp.cloud.aws;


import com.itrjp.cloud.aws.storage.AmazonS3Adapter;
import com.itrjp.cloud.base.CloudFactory;
import com.itrjp.cloud.base.storage.ObjectStorageService;
import com.itrjp.cloud.properties.AmazonProperties;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 16:17
 */
public class AmazonFactory implements CloudFactory {
    private final AmazonProperties properties;
    public final AwsBasicCredentials credentials;

    public AmazonFactory(AmazonProperties properties) {
        this.properties = properties;
        credentials = AwsBasicCredentials.create(
                properties.getAccessKeyId(),
                properties.getSecretAccessKey());
    }


    @Override
    public ObjectStorageService createObjectStorage() {

        S3Client s3Client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
        return new AmazonS3Adapter(s3Client, properties);
    }
}
