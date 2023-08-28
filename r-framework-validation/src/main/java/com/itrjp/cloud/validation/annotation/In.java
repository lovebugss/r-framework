package com.itrjp.cloud.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/29 15:07
 */
@Target({FIELD})
@Retention(RUNTIME)
@Repeatable(In.List.class)
@Documented
@Constraint(validatedBy = InValidator.class)
public @interface In {
    String message() default "参数可选值错误";

    /**
     * 分组
     *
     * @return
     */
    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};

    @Target({FIELD})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        In[] value();
    }
}
