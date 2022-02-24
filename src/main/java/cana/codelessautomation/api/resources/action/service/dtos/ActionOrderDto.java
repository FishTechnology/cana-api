package cana.codelessautomation.api.resources.action.service.dtos;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;
import lombok.Data;

@Data
public class ActionOrderDto {
    private Long actionId;
    private Long oldExecutionOrder;
    private Long currentExecutionOrder;
    private ActionDao actionDao;
}
