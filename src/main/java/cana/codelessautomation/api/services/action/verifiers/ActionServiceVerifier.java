package cana.codelessautomation.api.services.action.verifiers;

import cana.codelessautomation.api.services.action.dtos.CreateActionDto;
import cana.codelessautomation.api.services.action.dtos.GetActionsByTestCaseIdDto;
import cana.codelessautomation.api.services.action.repositories.daos.ActionDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;

import java.util.List;

public interface ActionServiceVerifier {
    List<ErrorMessageDto> verifyCreateAction(CreateActionDto createActionDto);

    List<ErrorMessageDto> isTestCaseIdValid(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto);

    List<ErrorMessageDto> isTestCaseIdValid(CreateActionDto createActionDto);

    List<ErrorMessageDto> verifyGetActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto);

    KeyValue<List<ErrorMessageDto>, ActionDao> isActionIdIsValid(Long actionId);
}
