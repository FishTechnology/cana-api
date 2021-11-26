package cana.codelessautomation.api.services.results.action;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;

import java.util.List;

public interface ActionResultService {
    List<ErrorMessageDto> updateActionResult(UpdateActionResultDto updateActionResultDto);

    List<ActionResultDao> getActionResultsByTestCaseResultId(Long testCaseResultId);
}
