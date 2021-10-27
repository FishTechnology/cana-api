package cana.codelessautomation.api.commons.exceptions;

import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;

import java.util.List;

public class ValidationException extends RuntimeException {
    public List<ErrorMessageModel> errorMessageModels;

    public ValidationException(List<ErrorMessageModel> errorMessageModels) {
        this(errorMessageModels, true);
    }

    public ValidationException(List<ErrorMessageModel> errorMessageModels,
                               boolean suppressStacktrace) {
      //  super("", null, suppressStacktrace, !suppressStacktrace);
        this.errorMessageModels = errorMessageModels;
    }
}
