package cana.codelessautomation.api.resources.action.service.verifiers;

import cana.codelessautomation.api.resources.action.service.dtos.CreateActionDto;
import cana.codelessautomation.api.resources.action.service.dtos.GetActionsByTestCaseIdDto;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;

import java.util.List;

public interface ActionServiceVerifier {
    List<ErrorMessageDto> verifyCreateAction(CreateActionDto createActionDto);

    List<ErrorMessageDto> isTestCaseIdValid(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto);

    List<ErrorMessageDto> isTestCaseIdValid(CreateActionDto createActionDto);

    List<ErrorMessageDto> verifyGetActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto);

    KeyValue<List<ErrorMessageDto>, ActionDao> isActionIdIsValid(Long actionId);
}
