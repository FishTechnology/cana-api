package cana.codelessautomation.api.resources.action.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.services.action.errorcodes.ActionErrorCode;
import cana.codelessautomation.api.services.action.repositories.daos.ActionOptionTypeDao;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateActionOptionModel {
    @NotEmpty(message = ActionErrorCode.getActionOptionTypeIsEmpty)
    @NotNull(message = ActionErrorCode.getActionOptionTypeIsNull)
    @ValidEnumString(enumRef = ActionOptionTypeDao.class, isOptional = true , message = ActionErrorCode.getActionOptionTypeIsInValid)
    private String optionType;
    private Long waitDuration;
    private Long order;
}
