package cana.codelessautomation.api.resources.action.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.resources.action.service.errorcodes.ActionErrorCode;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionTypeDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ConditionType;
import cana.codelessautomation.api.resources.action.service.repositories.daos.UIActionTypeDao;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateActionModel {
    private String key;
    private String value;
    private String comments;
    @NotNull(message = ActionErrorCode.getActionTypeIsNull)
    @NotEmpty(message = ActionErrorCode.getActionTypeIsEmpty)
    @ValidEnumString(enumRef = ActionTypeDao.class, message = ActionErrorCode.getActionTypeIsInValid)
    private String type;
    @NotNull(message = ActionErrorCode.getActionTypeIsNull)
    @NotEmpty(message = ActionErrorCode.getActionTypeIsEmpty)
    @ValidEnumString(enumRef = UIActionTypeDao.class, message = ActionErrorCode.getActionTypeIsInValid)
    private String uiActionType;
    @Valid
    private Long userId;
    @Valid
    private Boolean isAssertVerification;
    @Valid
    private List<CreateActionOptionModel> uiControlOptions;
    @Valid
    private List<CreateActionKeyModel> uiActionKeys;
    @Valid
    private BrowserDetailModel browserOptions;
    private Boolean isOptional;
    @ValidEnumString(enumRef = ConditionType.class, isOptional = true, message = ActionErrorCode.getActionConditionTypeInValid)
    private String conditionType;
}
