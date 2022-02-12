package cana.codelessautomation.api.resources.result.actionoptionresult.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.dto.UpdateActionOptionResultDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;

import java.util.List;

public interface ActionOptionResultVerifier {
    List<ErrorMessageDto> verifyGetActionResultsByTestCaseResultId(Long actionResultId);

    List<ErrorMessageDto> isActionResultIdValid(Long actionResultId);

    List<ErrorMessageDto> verifyUpdateActionOptionResult(UpdateActionOptionResultDto updateActionOptionResultDto);

    List<ErrorMessageDto> isActionResultId(UpdateActionOptionResultDto updateActionOptionResultDto);

    List<ErrorMessageDto> isActionOptionResultIdValid(UpdateActionOptionResultDto updateActionOptionResultDto);

    KeyValue<List<ErrorMessageDto>, ActionOptionResultDao> isActionOptionResultIdValid(Long actionOptionResultId);
}
