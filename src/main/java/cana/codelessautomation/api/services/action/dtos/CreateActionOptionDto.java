package cana.codelessautomation.api.services.action.dtos;

import cana.codelessautomation.api.services.action.repositories.daos.ActionOptionTypeDao;
import lombok.Data;

@Data
public class CreateActionOptionDto {
    private ActionOptionTypeDao optionType;
    private Long waitDuration;
    private Long order;
}
