package cana.codelessautomation.api.resources.action.service.dtos;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionTypeDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.UIOptionContentTypeDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.UIOptionControlTypeDao;
import lombok.Data;

@Data
public class CreateActionOptionDto {
    private ActionOptionTypeDao optionType;
    private Long waitDuration;
    private Long order;
    private UIOptionControlTypeDao optionalControlType;
    private Long duration;
    private String value;
    private UIOptionContentTypeDao optionContentType;
}
