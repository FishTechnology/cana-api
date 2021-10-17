package cana.codelessautomation.api.services.environment.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvironmentErrorCode extends BaseErrorCode {

    public static final String getEnvironmentNameIsNull = "CanaApi.EnvironmentResource.{0}.{1}.Environment.Name.Duplicated";
    public static final String getEnvironmentNameIsEmpty = "CanaApi.EnvironmentResource.{0}.{1}.Environment.Name.Duplicated";
    public static final String getEnvironmentNameIsNotMin = "CanaApi.EnvironmentResource.{0}.{1}.Environment.Name.Duplicated";

    public String getDuplicateNameFound() {
        return "CanaApi.EnvironmentResource.CreateEnvironment." + getHttpMethod() + ".Environment.Name.Duplicated";
    }

    public String getEnvironmentIdNotFound() {
        return "CanaApi.EnvironmentResource.CreateEnvironment." + getHttpMethod() + ".Environment.Id.NotFound";
    }
}
