package cana.codelessautomation.api.services.envvariable.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvironmentVariableErrorCodes extends BaseErrorCode {
    public static final String getEnvironmentVariableKeyIsEmpty = "CanaApi.EnvVariableResource.{0}.{1}.EnvironmentVariable.Key.IsEmpty";
    public static final String getEnvironmentVariableKeyIsNull = "CanaApi.EnvVariableResource.{0}.{1}.EnvironmentVariable.Key.IsNull";
    public static final String getEnvironmentVariableValueIsEmpty = "CanaApi.EnvVariableResource.{0}.{1}.EnvironmentVariable.Value.IsEmpty";
    public static final String getEnvironmentVariableValueIsNull = "CanaApi.EnvVariableResource.{0}.{1}.EnvironmentVariable.Value.IsNull";
    public static final String getEnvironmentVariableTypeIsEmpty = "CanaApi.EnvVariableResource.{0}.{1}.EnvironmentVariable.Type.IsEmpty";
    public static final String getEnvironmentVariableTypeIsNull = "CanaApi.EnvVariableResource.{0}.{1}.EnvironmentVariable.Type.IsNull";
    public static final String getEnvironmentVariableValueIsInValid ="CanaApi.EnvVariableResource.{0}.{1}.EnvironmentVariable.Type.InValid";

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
