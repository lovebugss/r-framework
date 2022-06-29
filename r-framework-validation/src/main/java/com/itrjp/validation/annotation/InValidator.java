package com.itrjp.validation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * InValidator
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/29 15:08
 */
public class InValidator implements ConstraintValidator<In, Object> {
    @Override
    public void initialize(In constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}
