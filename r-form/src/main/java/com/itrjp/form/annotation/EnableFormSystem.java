package com.itrjp.form.annotation;

import com.itrjp.form.config.FormConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({FormConfiguration.class})
public @interface EnableFormSystem {
}
