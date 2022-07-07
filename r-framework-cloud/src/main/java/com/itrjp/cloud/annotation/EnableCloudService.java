package com.itrjp.cloud.annotation;

import com.itrjp.cloud.config.CloudAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用云服务操作
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/7/7 16:02
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import({CloudAutoConfiguration.class})
public @interface EnableCloudService {
}
