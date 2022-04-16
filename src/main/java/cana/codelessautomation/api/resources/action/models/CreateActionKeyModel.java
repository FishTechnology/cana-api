package cana.codelessautomation.api.resources.action.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.resources.action.service.errorcodes.ActionErrorCode;
import cana.codelessautomation.api.resources.action.service.repositories.daos.UIKey;
import lombok.Data;

@Data
public class CreateActionKeyModel {
    private Long orderNumber;
    @ValidEnumString(enumRef = UIKey.class, message = ActionErrorCode.getKeyIsInValid, isOptional = true)
    private String key;
}
