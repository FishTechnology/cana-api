package cana.codelessautomation.api.services.globalvariable.dtos;

import cana.codelessautomation.api.services.action.repositories.daos.ActionOptionTypeDao;
import lombok.Data;

@Data
public class UIControlOptionDto {
    private ActionOptionTypeDao optionType;
    private Long waitDuration;
    private Long order;
}
