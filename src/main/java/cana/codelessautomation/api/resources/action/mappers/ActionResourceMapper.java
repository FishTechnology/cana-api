package cana.codelessautomation.api.resources.action.mappers;

import cana.codelessautomation.api.resources.action.models.CreateActionModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.services.action.dtos.CreateActionDto;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

import java.util.List;

public interface ActionResourceMapper {
    CreateActionDto mapCreateActionDto(CreateActionModel createActionModel, Long testCaseId);

    ResultModel mapResultModel(CreateActionDto createActionDto, List<ErrorMessageDto> errorMessages);
}
