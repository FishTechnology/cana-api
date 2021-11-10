package cana.codelessautomation.api.resources.action.mappers;

import cana.codelessautomation.api.resources.action.models.ActionDetailModel;
import cana.codelessautomation.api.resources.action.models.CreateActionModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduledActionDetailModel;
import cana.codelessautomation.api.services.action.dtos.CreateActionDto;
import cana.codelessautomation.api.services.action.dtos.GetActionsByTestCaseIdDto;
import cana.codelessautomation.api.services.action.repositories.daos.ActionDao;
import cana.codelessautomation.api.services.action.repositories.daos.entities.ActionDaoEntity;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

import java.util.List;

public interface ActionResourceMapper {
    CreateActionDto mapCreateActionDto(CreateActionModel createActionModel, Long testCaseId);

    ResultModel mapResultModel(CreateActionDto createActionDto, List<ErrorMessageDto> errorMessages);

    GetActionsByTestCaseIdDto mapGetActionsByTestCaseIdDto(Long testCaseId);

    List<ActionDetailModel> mapActionDetailModels(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto);

    ActionDetailModel mapActionDetailModel(ActionDao actionDao);

    ScheduledActionDetailModel mapScheduledActionDetailModel(ActionDaoEntity actionDaoEntity);
}
