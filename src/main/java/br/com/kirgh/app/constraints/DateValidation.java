package br.com.kirgh.app.constraints;

import br.com.kirgh.app.validators.DateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The code snippet is defining a custom annotation called {@code DateValidation}.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface DateValidation {
    /**
     * The {@code String message()} method within the {@code DateValidation} annotation is used to specify the error
     * message that will be displayed when the validation fails. In this case, the default error message is
     * {@code} not a valid representation of a past date {@code [yyyy-MM-dd]}. This message will be shown if the
     * annotated field or parameter does not represent a valid past date in the format "yyyy-MM-dd".
     *
     * @return Default message related to this validation
     */
    String message() default "not a valid representation of a past date [yyyy-MM-dd]";

    /**
     * This a method declaration within the {@code DateValidation} annotation. It is used to
     * specify the validation groups to which the annotation belongs.
     *
     * @return The method is returning an array of {@code Class}
     */
    Class<?>[] groups() default {};

    /**
     * This is a method declaration within the {@code DateValidation} annotation. It is used
     * to specify the payload types that can be attached to the validation constraint.
     *
     * @return The method is returning an array of {@code Class}
     */
    Class<? extends Payload>[] payload() default {};
}
