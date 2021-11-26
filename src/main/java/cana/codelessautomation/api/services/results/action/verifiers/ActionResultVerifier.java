package cana.codelessautomation.api.services.results.action.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;

import java.util.List;

public interface ActionResultVerifier {
    List<ErrorMessageDto> verifyUpdateActionResult(UpdateActionResultDto updateActionResultDto);

    List<ErrorMessageDto> isActionIdValid(UpdateActionResultDto updateActionResultDto);

    List<ErrorMessageDto> isActionResultIdValid(UpdateActionResultDto updateActionResultDto);

    KeyValue<List<ErrorMessageDto>, ActionResultDao> isActionResultIdValid(Long actionResultId);

    List<ErrorMessageDto> isTestCaseResultIdValid(UpdateActionResultDto updateActionResultDto);

    List<ErrorMessageDto> verifyGetActionResultsByTestCaseResultId(Long testCaseResultId);

    List<ErrorMessageDto> isTestCaseResultIdValid(Long testCaseResultId);
}
