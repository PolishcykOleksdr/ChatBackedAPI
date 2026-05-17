package com.test.task.chat_system.annotation;

import com.test.task.chat_system.validator.MinLengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: user,
 * date: 17.05.2026
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinLengthValidator.class)
public @interface MinLength {
    int min() default 3;
    String message() default "Length of the text should be less than {min}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}