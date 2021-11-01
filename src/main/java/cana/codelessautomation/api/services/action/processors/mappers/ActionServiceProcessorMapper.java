package cana.codelessautomation.api.services.action.processors.mappers;

import cana.codelessautomation.api.services.action.dtos.CreateActionDto;
import cana.codelessautomation.api.services.action.dtos.CreateActionOptionDto;
import cana.codelessautomation.api.services.action.repositories.daos.ActionDao;
import cana.codelessautomation.api.services.action.repositories.daos.ActionOptionDao;

public interface ActionServiceProcessorMapper {
    ActionDao mapActionDao(CreateActionDto createActionDto);
    ActionOptionDao mapActionOptionDao(CreateActionDto createActionDto, CreateActionOptionDto createActionOptionDto);
}
