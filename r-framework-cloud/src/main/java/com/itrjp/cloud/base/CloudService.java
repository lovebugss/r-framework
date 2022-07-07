package com.itrjp.cloud.base;

import com.itrjp.cloud.consts.CloudType;

/**
 * CloudService
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 16:18
 */
public interface CloudService {
    CloudType getCloudType();

    /**
     * 是否匹配云类型
     *
     * @param cloudType
     * @return
     */
    default boolean isMatch(CloudType cloudType) {
        // 当前云类型
        CloudType currCloudType = getCloudType();
        return cloudType.equals(currCloudType);
    }
}
