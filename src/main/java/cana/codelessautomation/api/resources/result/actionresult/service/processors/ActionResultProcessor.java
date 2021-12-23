package cana.codelessautomation.api.resources.result.actionresult.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.actionresult.service.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;

import java.util.List;

public interface ActionResultProcessor {
    
    List<ErrorMessageDto> processUpdateActionResult(UpdateActionResultDto updateActionResultDto);

    List<ErrorMessageDto> updateActionResult(UpdateActionResultDto updateActionResultDto);

    List<ActionResultDao> processGetActionResultsByTestCaseResultId(Long testCaseResultId);
}
