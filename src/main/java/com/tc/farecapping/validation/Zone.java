package com.tc.farecapping.validation;

import java.lang.annotation.Documented;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ FIELD, LOCAL_VARIABLE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ZoneValidator.class })
public @interface Zone {

    String message() default "{com.example.validation.ValidAddress.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}