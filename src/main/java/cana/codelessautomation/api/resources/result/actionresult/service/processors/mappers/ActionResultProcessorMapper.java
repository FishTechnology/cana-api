package cana.codelessautomation.api.resources.result.actionresult.service.processors.mappers;

import cana.codelessautomation.api.resources.result.actionresult.service.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;

public interface ActionResultProcessorMapper {
    ActionResultDao mapActionResultDao(UpdateActionResultDto updateActionResultDto);
}
