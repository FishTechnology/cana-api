package cana.codelessautomation.api.resources.action.service.processors;

import cana.codelessautomation.api.resources.action.service.dtos.CreateActionDto;
import cana.codelessautomation.api.resources.action.service.dtos.GetActionsByTestCaseIdDto;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;

import java.util.List;

public interface ActionServiceProcessor {
    List<ErrorMessageDto> processCreateAction(CreateActionDto createActionDto);

    List<ErrorMessageDto> getActionOrder(CreateActionDto createActionDto);

    List<ErrorMessageDto> createActionOption(CreateActionDto createActionDto);

    List<ErrorMessageDto> createAction(CreateActionDto createActionDto);

    List<ErrorMessageDto> processGetActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto);

    List<ErrorMessageDto> getActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto);
}
