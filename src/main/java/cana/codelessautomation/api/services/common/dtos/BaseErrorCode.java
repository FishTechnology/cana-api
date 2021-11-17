package cana.codelessautomation.api.services.common.dtos;

import io.vertx.core.http.HttpServerRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;


@Provider
@ApplicationScoped
public class BaseErrorCode {
    @Inject
    HttpServerRequest httpServerRequest;

    @Context
    ResourceContext resourceContext;

    public String getHttpMethod() {
        return httpServerRequest.method().name();
    }

    public String getResourceName() {
        // return resourceContext.getClass().getCanonicalName();
        return "Name";
    }

    public String getResourceActionName() {
        // return resourceContext.getClass().getCanonicalName();
        return "Name";
    }
}
