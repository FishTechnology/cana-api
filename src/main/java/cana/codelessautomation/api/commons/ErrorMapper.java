package cana.codelessautomation.api.commons;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
            //exceptionJson.put("error", validationException.errorMessageModels[0]);
//             code = ((ValidationException) exception).getResponse().getStatus();
        }

        exceptionJson.put("exceptionType", exception.getClass().getName());
        // exceptionJson.put("code", code);

        if (exception.getMessage() != null) {
            exceptionJson.put("error", exception.getMessage());
        }

//        if (exception.err() != null) {
//            exceptionJson.put("error", exception.getMessage());
//        }

        return Response.status(code)
                .entity(exceptionJson)
                .build();
    }
}
