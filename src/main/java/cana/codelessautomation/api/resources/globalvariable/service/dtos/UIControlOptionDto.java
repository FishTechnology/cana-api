package cana.codelessautomation.api.resources.globalvariable.service.dtos;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionTypeDao;
import lombok.Data;

@Data
public class UIControlOptionDto {
    private ActionOptionTypeDao optionType;
    private Long waitDuration;
    private Long order;
}
