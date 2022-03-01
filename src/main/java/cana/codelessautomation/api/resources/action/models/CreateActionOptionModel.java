package cana.codelessautomation.api.resources.action.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.resources.action.service.errorcodes.ActionErrorCode;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionTypeDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.UIOptionConditionTypeDao;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;

@Data
public class CreateActionOptionModel {
    @NotEmpty(message = ActionErrorCode.getActionOptionTypeIsEmpty)
    @NotNull(message = ActionErrorCode.getActionOptionTypeIsNull)
    @ValidEnumString(enumRef = ActionOptionTypeDao.class, isOptional = true, message = ActionErrorCode.getActionOptionTypeIsInValid)
    private String optionType;
    private Long waitDuration;
    private Long order;
    //@NotEmpty(message = ActionErrorCode.getActionUIOptionConditionTypeIsEmpty)
    //@NotNull(message = ActionErrorCode.getActionUIOptionConditionTypeIsNull)
    @ValidEnumString(enumRef = UIOptionConditionTypeDao.class, isOptional = true, message = ActionErrorCode.getActionUIOptionConditionTypeIsInValid)
    private String conditionType;

    @DefaultValue("4")
    private Long duration;
}
