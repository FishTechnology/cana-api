package cana.codelessautomation.api.resources.action.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.resources.action.service.errorcodes.ActionErrorCode;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionTypeDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.UIOptionContentTypeDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.UIOptionControlTypeDao;
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
    @ValidEnumString(enumRef = UIOptionControlTypeDao.class, isOptional = true, message = ActionErrorCode.getActionUIOptionControlTypeIsInValid)
    private String OptionControlType;

    @ValidEnumString(enumRef = UIOptionContentTypeDao.class, isOptional = true, message = ActionErrorCode.getActionUIOptionContentTypeIsInValid)
    private String optionContentType;

    @DefaultValue("4")
    private Long duration;

    @DefaultValue("false")
    private Boolean isValueValidation;

    @DefaultValue("4")
    private String value;
}
