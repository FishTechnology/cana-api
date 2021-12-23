package cana.codelessautomation.api.resources.result.actionresult.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.result.actionresult.service.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;

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
