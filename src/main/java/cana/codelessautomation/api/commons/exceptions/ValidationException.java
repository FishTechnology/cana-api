package cana.codelessautomation.api.commons.exceptions;

import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import lombok.Data;

import java.util.List;

@Data
public class ValidationException extends Exception {
    private List<ErrorMessageModel> errorMessageModels;

    public ValidationException(List<ErrorMessageModel> errorMessageModels) {
        this(errorMessageModels,true);
    }

    public ValidationException(List<ErrorMessageModel> errorMessageModels,
                               boolean suppressStacktrace) {
        super("", null, suppressStacktrace, !suppressStacktrace);
        this.errorMessageModels = errorMessageModels;
    }
}
