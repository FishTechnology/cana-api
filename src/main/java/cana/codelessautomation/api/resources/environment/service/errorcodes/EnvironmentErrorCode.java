package cana.codelessautomation.api.resources.environment.service.errorcodes;

import cana.codelessautomation.api.commons.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvironmentErrorCode extends BaseErrorCode {

    public static final String getEnvironmentNameIsNull = "CanaApi.EnvironmentResource.{0}.{1}.Environment.Name.IsNull";
    public static final String getEnvironmentNameIsEmpty = "CanaApi.EnvironmentResource.{0}.{1}.Environment.Name.IsEmpty";
    public static final String getEnvironmentNameIsNotMin = "CanaApi.EnvironmentResource.{0}.{1}.Environment.Name.IsLessThan.3";

    public String getDuplicateNameFound() {
        return "CanaApi.EnvironmentResource.CreateEnvironment." + getHttpMethod() + ".Environment.Name.Duplicated";
    }

    public String getEnvironmentIdNotFound() {
        return "CanaApi.EnvironmentResource.CreateEnvironment." + getHttpMethod() + ".Environment.Id.NotFound";
    }
}
