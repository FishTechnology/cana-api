package cana.codelessautomation.api.services.common.dtos;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.net.http.HttpRequest;


@Provider
public class BaseErrorCode {
    @Context
    HttpRequest request;

    public String getHttpMethod() {
        return "GET";
    }

}
