package cana.codelessautomation.api.resources.result.actionresult.mappers;

import cana.codelessautomation.api.resources.result.actionresult.models.UpdateActionResultModel;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;

public interface ActionResultMapper {
    UpdateActionResultDto mapUpdateActionResultDto(Long testCaseResultId, Long actionResultId, UpdateActionResultModel updateActionResultModel);
}
