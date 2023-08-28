package com.itrjp.cloud;

import com.itrjp.cloud.storage.ObjectStorageService;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 16:18
 */
public interface CloudFactory {


    /**
     * 创建对象存储service
     *
     * @return
     */
    ObjectStorageService createObjectStorage();
}
