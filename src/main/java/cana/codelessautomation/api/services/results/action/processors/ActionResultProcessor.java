package cana.codelessautomation.api.services.results.action.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;

import java.util.List;

public interface ActionResultProcessor {
    
    List<ErrorMessageDto> processUpdateActionResult(UpdateActionResultDto updateActionResultDto);

    List<ErrorMessageDto> updateActionResult(UpdateActionResultDto updateActionResultDto);

    List<ActionResultDao> processGetActionResultsByTestCaseResultId(Long testCaseResultId);
}
