package cana.codelessautomation.api.services.envvariable.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvironmentVariableErrorCodes extends BaseErrorCode {
    public String getEnvVariableIdNotFound() {
        return "CanaApi.EnvVariableResource.deleteEnvVariable." + getHttpMethod() + ".EnvironmentVariableId.NotFound";
    }

    public String getEnvVarIdNotAssociatedUser() {
        return "CanaApi.EnvVariableResource.deleteEnvVariable." + getHttpMethod() + ".EnvironmentVariableId.Not.AssociatedUser";
    }

    public String getEnvVariableKeyDuplicate() {
        return "CanaApi.EnvVariableResource.createEnvVariable." + getHttpMethod() + ".EnvironmentVariable.Key.Duplicated";
    }
}
