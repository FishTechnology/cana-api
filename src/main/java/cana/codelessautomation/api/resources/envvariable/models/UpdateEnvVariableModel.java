package cana.codelessautomation.api.resources.envvariable.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.services.envvariable.errorcodes.EnvironmentVariableErrorCodes;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableType;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateEnvVariableModel {
    @Valid
    @NotEmpty(message = EnvironmentVariableErrorCodes.getEnvironmentVariableKeyIsEmpty)
    @NotNull(message = EnvironmentVariableErrorCodes.getEnvironmentVariableKeyIsNull)
    private String key;
    @Valid
    @NotEmpty(message = EnvironmentVariableErrorCodes.getEnvironmentVariableValueIsEmpty)
    @NotNull(message = EnvironmentVariableErrorCodes.getEnvironmentVariableValueIsNull)
    private String value;
    private Long userId;
    @Valid
    @NotEmpty(message = EnvironmentVariableErrorCodes.getEnvironmentVariableTypeIsEmpty)
    @NotNull(message = EnvironmentVariableErrorCodes.getEnvironmentVariableTypeIsNull)
    @ValidEnumString(enumRef = EnvironmentVariableType.class, message = EnvironmentVariableErrorCodes.getEnvironmentVariableValueIsInValid)
    private String type;
    private String comments;
    private String content;
}
