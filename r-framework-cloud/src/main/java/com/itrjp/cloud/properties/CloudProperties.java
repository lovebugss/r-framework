package com.itrjp.cloud.properties;

import com.itrjp.cloud.consts.CloudType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 15:31
 */

@ConfigurationProperties("im.cloud")
public class CloudProperties {

    private CloudType defaultType;

    private AmazonProperties amazon = new AmazonProperties();
    private AliyunProperties aliyun = new AliyunProperties();

    public CloudType getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(CloudType defaultType) {
        this.defaultType = defaultType;
    }

    public AmazonProperties getAmazon() {
        return amazon;
    }

    public void setAmazon(AmazonProperties amazon) {
        this.amazon = amazon;
    }

    public AliyunProperties getAliyun() {
        return aliyun;
    }

    public void setAliyun(AliyunProperties aliyun) {
        this.aliyun = aliyun;
    }
}
