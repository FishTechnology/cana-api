package cana.codelessautomation.api.services.results.action.processors.mappers;

import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;

public interface ActionResultProcessorMapper {
    ActionResultDao mapActionResultDao(UpdateActionResultDto updateActionResultDto);
}
