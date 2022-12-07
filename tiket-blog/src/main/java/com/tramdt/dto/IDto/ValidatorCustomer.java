package com.tramdt.dto.IDto;

import com.tramdt.dto.CustomerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CustomerValidator.class})
public @interface ValidatorCustomer {
    String message() default "{}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
