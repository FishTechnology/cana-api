package cana.codelessautomation.api.commons;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import org.apache.commons.collections.CollectionUtils;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ErrorMapper implements ExceptionMapper<Exception> {
    @Inject
    ObjectMapper objectMapper;

    @Override
    public Response toResponse(Exception exception) {

        int code = 500;
        ObjectNode exceptionJson = objectMapper.createObjectNode();

        if (exception instanceof ValidationException) {
            var validationException = ((ValidationException) exception);
            if (CollectionUtils.isNotEmpty(validationException.errorMessageModels)) {
                exceptionJson.put("error", new Gson().toJson(validationException.errorMessageModels));
            }
        }

        if (exception.getMessage() != null) {
            exceptionJson.put("error", exception.getMessage());
        }

        return Response.status(code)
                .entity(exceptionJson)
                .build();
    }
}
