package cana.codelessautomation.api.services.common.dtos;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.net.http.HttpRequest;


@Provider
//@PreMatching
@ApplicationScoped
public class BaseErrorCode implements ContainerRequestFilter {
    @Context
    HttpRequest request;

    public String getHttpMethod() {
        return "GET";
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
        return;
    }
}
