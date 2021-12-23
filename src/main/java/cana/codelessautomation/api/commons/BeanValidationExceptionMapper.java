package cana.codelessautomation.api.commons;

import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.commons.utilities.CanaUtility;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.Response.status;

@Provider
public class BeanValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<ErrorMessageModel> errorMsgModels = new ArrayList<>();
        exception.getConstraintViolations().forEach(constraint -> {
            var msgTemplate = constraint.getMessageTemplate();
            errorMsgModels.addAll(CanaUtility.getErrorMessageModels(msgTemplate));
        });
        return status(Response.Status.BAD_REQUEST).entity(errorMsgModels).build();
    }
}
