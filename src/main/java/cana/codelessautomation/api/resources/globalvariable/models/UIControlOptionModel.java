package cana.codelessautomation.api.resources.globalvariable.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.services.action.repositories.daos.ActionOptionTypeDao;
import cana.codelessautomation.api.services.globalvariable.errorcodes.GlobalVariableErrorCode;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UIControlOptionModel {
    @ValidEnumString(enumRef = ActionOptionTypeDao.class,message = GlobalVariableErrorCode.getOptionTypeIsInValid)
    @NotNull(message = GlobalVariableErrorCode.getOptionTypeIsNull)
    @NotEmpty(message = GlobalVariableErrorCode.getOptionTypeIsEmpty)
    private String optionType;
    private Long waitDuration;
    private Long order;
}
