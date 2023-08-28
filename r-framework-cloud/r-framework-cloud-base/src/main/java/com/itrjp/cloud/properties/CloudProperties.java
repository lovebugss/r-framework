package com.itrjp.cloud.properties;

import com.itrjp.cloud.consts.CloudType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 15:31
 */
@ConfigurationProperties(prefix = "r.cloud")
public class CloudProperties {

    private CloudType defaultType;

    public CloudType getDefaultType() {
        return defaultType;
    }
}
