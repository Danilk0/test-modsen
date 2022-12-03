package com.example.demomodsen.validation;

import com.example.demomodsen.validation.impl.ValidOrganizerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidOrganizerValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOrganizer {

    String message() default "No such organizer exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
