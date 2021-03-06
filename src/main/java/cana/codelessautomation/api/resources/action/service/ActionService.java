package cana.codelessautomation.api.resources.action.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.action.service.dtos.CreateActionDto;
import cana.codelessautomation.api.resources.action.service.dtos.DeleteActionByIdDto;
import cana.codelessautomation.api.resources.action.service.dtos.GetActionsByTestCaseIdDto;
import cana.codelessautomation.api.resources.action.service.dtos.UpdateActionOrderDto;

import java.util.List;

public interface ActionService {
    List<ErrorMessageDto> createAction(CreateActionDto createActionDto);

    List<ErrorMessageDto> getActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto);

    List<ErrorMessageDto> deleteActionById(DeleteActionByIdDto deleteActionByIdDto);

    List<ErrorMessageDto> updateActionOrder(UpdateActionOrderDto updateActionOrderDto);
}
