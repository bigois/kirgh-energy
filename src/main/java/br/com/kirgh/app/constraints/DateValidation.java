package br.com.kirgh.app.constraints;

import br.com.kirgh.app.validators.DateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface DateValidation {
    String message() default "not a valid representation of a past date [yyyy-MM-dd]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
