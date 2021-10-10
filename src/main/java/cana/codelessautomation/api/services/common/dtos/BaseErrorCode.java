package cana.codelessautomation.api.services.common.dtos;

import io.vertx.core.http.HttpServerRequest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Context;

@RequestScoped
public class BaseErrorCode {
    @Context
    HttpServerRequest request;


    public String getHttpMethod() {
        return "GET";
    }

}
