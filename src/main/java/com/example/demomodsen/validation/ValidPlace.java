package com.example.demomodsen.validation;

import com.example.demomodsen.validation.impl.ValidPlaceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidPlaceValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPlace {

    String message() default "No such place exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
