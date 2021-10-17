package cana.codelessautomation.api.resources.environment.models;

import cana.codelessautomation.api.services.environment.errorcodes.EnvironmentErrorCode;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateEnvironmentModel {
    @NotNull(message = EnvironmentErrorCode.getEnvironmentNameIsNull)
    @NotEmpty(message = EnvironmentErrorCode.getEnvironmentNameIsEmpty)
    @Size(message = EnvironmentErrorCode.getEnvironmentNameIsNotMin, min = 3)
    private String name;
    private Long userId;
    private String comments;
}
