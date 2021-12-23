package cana.codelessautomation.api.resources.action.service.processors.mappers;

import cana.codelessautomation.api.resources.action.service.dtos.CreateActionDto;
import cana.codelessautomation.api.resources.action.service.dtos.CreateActionOptionDto;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionDao;

public interface ActionServiceProcessorMapper {
    ActionDao mapActionDao(CreateActionDto createActionDto);
    ActionOptionDao mapActionOptionDao(CreateActionDto createActionDto, CreateActionOptionDto createActionOptionDto);
}
