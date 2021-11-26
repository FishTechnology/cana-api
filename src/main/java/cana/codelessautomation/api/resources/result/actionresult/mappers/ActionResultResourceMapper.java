package cana.codelessautomation.api.resources.result.actionresult.mappers;

import cana.codelessautomation.api.resources.result.actionresult.models.ActionResultModel;
import cana.codelessautomation.api.resources.result.actionresult.models.UpdateActionResultModel;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;

import java.util.List;

public interface ActionResultResourceMapper {
    UpdateActionResultDto mapUpdateActionResultDto(Long testCaseResultId, Long actionResultId, UpdateActionResultModel updateActionResultModel);

    List<ActionResultModel> mapActionResultModels(List<ActionResultDao> actionResultDtos);
}
