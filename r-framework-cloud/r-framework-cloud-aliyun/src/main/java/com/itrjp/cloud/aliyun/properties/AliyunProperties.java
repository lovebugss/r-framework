package com.itrjp.cloud.aliyun.properties;

/**
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 16:32
 */
public class AliyunProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
