package br.com.kirgh.app.validators;

import br.com.kirgh.app.constraints.DateValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The DateValidator class is a custom constraint validator for validating date strings.
 */
public class DateValidator implements ConstraintValidator<DateValidation, String> {
   /**
    * The function checks if a given string is a valid date in the format "yyyy-MM-dd" and if it is a
    * past date.
    * 
    * @param value The value parameter is a string representing a date in the format "yyyy-MM-dd".
    * @param context The `ConstraintValidatorContext` is an interface that provides contextual
    * information and operations for constraint validators. It allows you to customize the validation
    * process and provides methods for adding constraint violation messages, retrieving the validated
    * value, and accessing the metadata of the constraint being validated.
    * @return The method is returning a boolean value.
    */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
            Matcher matcher = pattern.matcher(value);

            if (!matcher.matches()) {
                return false;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);

            try {
                Date date = dateFormat.parse(value);

                if (!date.before(Calendar.getInstance().getTime())) {
                    return false;
                }
            } catch (ParseException e) {
                return false;
            }
        }

        return true;
    }
}
