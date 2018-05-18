package com.logos.validator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = CheckPasswordValidator.class)
public @interface CheckPassword {

	String message() default "Passwords do not match";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
