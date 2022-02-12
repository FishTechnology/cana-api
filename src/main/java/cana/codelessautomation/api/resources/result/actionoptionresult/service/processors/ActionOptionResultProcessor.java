package cana.codelessautomation.api.resources.result.actionoptionresult.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.dto.UpdateActionOptionResultDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;

import java.util.List;

public interface ActionOptionResultProcessor {
    List<ActionOptionResultDao> processGetActionResultsByTestCaseResultId(Long actionResultId);

    List<ErrorMessageDto> processUpdateActionOptionResult(UpdateActionOptionResultDto updateActionOptionResultDto);

    List<ErrorMessageDto> updateActionOptionResult(UpdateActionOptionResultDto updateActionOptionResultDto);
}
