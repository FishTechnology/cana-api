package cana.codelessautomation.api.resources.environment.models;

import cana.codelessautomation.api.resources.customer.service.errorcodes.CustomerErrorCode;
import cana.codelessautomation.api.resources.environment.service.errorcodes.EnvironmentErrorCode;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateEnvironmentModel {
    @Valid
    @NotNull(message = EnvironmentErrorCode.getEnvironmentNameIsNull)
    @NotEmpty(message = EnvironmentErrorCode.getEnvironmentNameIsEmpty)
    @Size(message = EnvironmentErrorCode.getEnvironmentNameIsNotMin, min = 3)
    private String name;
    @Min(message = CustomerErrorCode.getUserIdIsZero, value = 1)
    private Long userId;
    private String comments;
}
