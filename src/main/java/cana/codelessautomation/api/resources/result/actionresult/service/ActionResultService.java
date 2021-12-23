package cana.codelessautomation.api.resources.result.actionresult.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.actionresult.service.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;

import java.util.List;

public interface ActionResultService {
    List<ErrorMessageDto> updateActionResult(UpdateActionResultDto updateActionResultDto);

    List<ActionResultDao> getActionResultsByTestCaseResultId(Long testCaseResultId);
}
