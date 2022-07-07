package com.itrjp.cloud.base.storage;

import java.io.File;

/**
 * 对象存储操作
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 15:37
 */
public interface ObjectStorageService {

    /**
     * 上传文件
     *
     * @param file
     */
    void uploadFile(String bucket, String file);

    /**
     * 上传文件
     *
     * @param file
     */
    void uploadFile(String bucket, File file);


    boolean has(String bucket, String key);

    void headObject(String bucket, String key);
}
