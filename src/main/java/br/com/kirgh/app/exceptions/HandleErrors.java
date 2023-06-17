package br.com.kirgh.app.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This Java class contains exception handlers for various types of exceptions and returns a map of error details for each
 * exception.
 */
@RestControllerAdvice
@SuppressWarnings("unused")
public class HandleErrors {
    private final String timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date().getTime());

    /**
     * This Java function handles validation exceptions by creating a map of field names and error messages.
     *
     * @param ex "ex" is an instance of the "MethodArgumentNotValidException" class, which is thrown when the validation of
     *           a method argument fails. This exception contains information about the validation errors that occurred.
     * @return A map of field names and their corresponding error messages, along with a timestamp. This is the result of
     * handling a MethodArgumentNotValidException, which occurs when a request parameter fails validation. The map is
     * created by iterating through all errors in the exception's BindingResult and extracting the field name and error
     * message for each FieldError.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        errors.put("timestamp", timestamp);
        return errors;
    }

    /**
     * This is a Java exception handler that returns a map of error details for internal server errors.
     *
     * @param ex "ex" is a variable representing the exception that was thrown. It is used in the method to catch any type
     *           of exception that may occur during the execution of the code.
     * @return A {@code Map} object containing the timestamp and an error message is being returned. This is the result of
     * handling an exception of type {@code Exception} with the {@code @ExceptionHandler} annotation and the {@code @ResponseStatus}
     * annotation indicating that the HTTP status code should be set to {@code INTERNAL_SERVER_ERROR}. The method logs the
     * exception stack trace and returns a map with the timestamp and error message.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handle(Exception ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("timestamp", timestamp);
        errors.put("message", "something goes wrong");

        ex.printStackTrace();
        return errors;
    }

    /**
     * This Java function handles the IllegalArgumentException and returns a map of error messages.
     *
     * @param ex "ex" is the variable name for the IllegalArgumentException object that is being caught by this exception
     *           handler method. It is used to access the properties and methods of the exception object, such as the error message.
     * @return A {@code Map<String, String>} containing the timestamp and error message is being returned. This is the result of
     * handling a {@code IllegalArgumentException} with a {@code @ResponseStatus} annotation that sets the HTTP status code to
     * {@code CONFLICT}. The method also prints the stack trace of the exception.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleConflit(IllegalArgumentException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("timestamp", timestamp);
        errors.put("message", ex.getMessage());

        ex.printStackTrace();
        return errors;
    }

    /**
     * This Java function handles the exception thrown when an entity is not found and returns a map of error details.
     *
     * @param ex {@code ex} is an instance of the {@code EntityNotFoundException} class, which is an exception thrown when an entity
     *           (such as a database record) is not found. The {@code @ExceptionHandler} annotation is used to handle this exception and
     *           return a custom error response with a {@code HttpStatus.NOT_FOUND} status code
     * @return A {@code Map} containing the timestamp and error message for the {@code EntityNotFoundException} that was caught.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String, String> handleNotFound(EntityNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("timestamp", timestamp);
        errors.put("message", ex.getMessage());

        ex.printStackTrace();
        return errors;
    }
}
