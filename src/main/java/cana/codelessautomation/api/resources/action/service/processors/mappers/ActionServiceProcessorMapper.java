package cana.codelessautomation.api.resources.action.service.processors.mappers;

import cana.codelessautomation.api.resources.action.service.dtos.*;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionKeyDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionDao;

public interface ActionServiceProcessorMapper {
    ActionDao mapActionDao(CreateActionDto createActionDto);

    ActionOptionDao mapActionOptionDao(CreateActionDto createActionDto, CreateActionOptionDto createActionOptionDto);

    ActionDao mapDeleteActionDao(ActionDao actionDao);

    ActionDao mapActionDao(UpdateActionOrderDto updateActionOrderDto, ActionOrderDto actionOrderDto);

    ActionKeyDao mapActionKeyDao(CreateActionDto createActionDto, UIKeyDetailDto uiKeyDetailDto);
}
