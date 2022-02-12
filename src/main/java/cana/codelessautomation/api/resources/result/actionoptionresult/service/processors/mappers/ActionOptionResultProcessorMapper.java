package cana.codelessautomation.api.resources.result.actionoptionresult.service.processors.mappers;

import cana.codelessautomation.api.resources.result.actionoptionresult.service.dto.UpdateActionOptionResultDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;

public interface ActionOptionResultProcessorMapper {
    ActionOptionResultDao mapActionOptionResultDao(UpdateActionOptionResultDto updateActionOptionResultDto);
}
