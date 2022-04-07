package cana.codelessautomation.api.resources.action.service.dtos;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionTypeDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.UIControlConditionTypeDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.UIOptionContentTypeDao;
import lombok.Data;

@Data
public class CreateActionOptionDto {
    private ActionOptionTypeDao optionType;
    private Long waitDuration;
    private Long order;
    private UIControlConditionTypeDao controlConditionType;
    private Long duration;
    private String value;
    private UIOptionContentTypeDao optionContentType;
}
