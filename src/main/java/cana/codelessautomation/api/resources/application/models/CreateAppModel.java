package cana.codelessautomation.api.resources.application.models;

import cana.codelessautomation.api.services.application.errorcodes.ApplicationErrorCode;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateAppModel {
    @NotNull(message = ApplicationErrorCode.getAppNameIsNullErrorCode)
    @NotEmpty(message = ApplicationErrorCode.getAppNameIsEmptyErrorCode)
    @Length(min = 5, message = ApplicationErrorCode.getAppNameIsMinErrorCode)
    private String name;
    //    @NotNull(message = CustomerErrorCode.getUserIdIsNullErrorCode)
//    @NotEmpty(message = CustomerErrorCode.getUserIdIsEmptyErrorCode)
    private Long userId;
    private String comments;
}
