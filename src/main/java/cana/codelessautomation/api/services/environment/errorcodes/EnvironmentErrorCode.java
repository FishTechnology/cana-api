package cana.codelessautomation.api.services.environment.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvironmentErrorCode extends BaseErrorCode {
    public String getDuplicateNameFound() {
        return "CanaApi.EnvironmentResource.CreateEnvironment." + getHttpMethod() + ".Environment.Name.Duplicated";
    }

    public String getEnvironmentIdNotFound() {
        return "CanaApi.EnvironmentResource.CreateEnvironment." + getHttpMethod() + ".Environment.Id.NotFound";
    }
}
