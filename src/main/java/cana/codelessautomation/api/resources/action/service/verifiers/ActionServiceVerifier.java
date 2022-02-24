package cana.codelessautomation.api.resources.action.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.action.service.dtos.CreateActionDto;
import cana.codelessautomation.api.resources.action.service.dtos.DeleteActionByIdDto;
import cana.codelessautomation.api.resources.action.service.dtos.GetActionsByTestCaseIdDto;
import cana.codelessautomation.api.resources.action.service.dtos.UpdateActionOrderDto;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;

import java.util.List;

public interface ActionServiceVerifier {
    List<ErrorMessageDto> verifyCreateAction(CreateActionDto createActionDto);

    List<ErrorMessageDto> isActionIdIsValid(DeleteActionByIdDto deleteActionByIdDto);

    List<ErrorMessageDto> isTestCaseIdValid(DeleteActionByIdDto deleteActionByIdDto);

    List<ErrorMessageDto> isTestCaseIdAndActionIdMapValid(DeleteActionByIdDto deleteActionByIdDto);

    List<ErrorMessageDto> isTestCaseIdValid(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto);

    List<ErrorMessageDto> isTestCaseIdValid(CreateActionDto createActionDto);

    List<ErrorMessageDto> verifyGetActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto);

    List<ErrorMessageDto> isActionIdsIsValid(UpdateActionOrderDto updateActionOrderDto);

    List<ErrorMessageDto> isTestCaseIdValid(UpdateActionOrderDto updateActionOrderDto);

    KeyValue<List<ErrorMessageDto>, ActionDao> isActionIdIsValid(Long actionId);

    List<ErrorMessageDto> verifyDeleteActionById(DeleteActionByIdDto deleteActionByIdDto);

    List<ErrorMessageDto> verifyUpdateActionOrder(UpdateActionOrderDto updateActionOrderDto);
}
