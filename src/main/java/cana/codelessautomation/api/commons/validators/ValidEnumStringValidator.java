package cana.codelessautomation.api.commons.validators;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEnumStringValidator implements ConstraintValidator<ValidEnumString, String> {
    private ValidEnumString _validEnumString;

    @Override
    public void initialize(ValidEnumString validEnumString) {
        this._validEnumString = validEnumString;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (this._validEnumString.isOptional() && StringUtils.isEmpty(value)) {
            return true;
        }
        return EnumUtils.isValidEnumIgnoreCase(this._validEnumString.enumRef(),value);
    }
}
