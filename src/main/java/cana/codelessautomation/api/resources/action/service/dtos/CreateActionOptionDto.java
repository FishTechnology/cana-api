package cana.codelessautomation.api.resources.action.service.dtos;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionTypeDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.UIOptionConditionTypeDao;
import lombok.Data;

@Data
public class CreateActionOptionDto {
    private ActionOptionTypeDao optionType;
    private Long waitDuration;
    private Long order;
    private UIOptionConditionTypeDao conditionType;
}
