package com.itrjp.cloud.web.annotation;

import com.itrjp.cloud.web.config.RequestLogAutoConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO 启用请求日志
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/23 17:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(RequestLogAutoConfigure.class)
@interface EnableWebLog {
}
