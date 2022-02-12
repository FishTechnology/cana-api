package cana.codelessautomation.api.resources.result.actionoptionresult.mapper;

import cana.codelessautomation.api.resources.result.actionoptionresult.models.ActionOptionResultModel;
import cana.codelessautomation.api.resources.result.actionoptionresult.models.UpdateActionOptionResultModel;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.dto.UpdateActionOptionResultDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;

import java.util.List;

public interface ActionOptionResultResourceMapper {
    List<ActionOptionResultModel> mapActionOptionResultModels(List<ActionOptionResultDao> actionOptionResultDtos);

    UpdateActionOptionResultDto mapUpdateActionOptionResultDto(Long actionResultId, Long actionOptionResultId, UpdateActionOptionResultModel updateActionOptionResultModel);
}
