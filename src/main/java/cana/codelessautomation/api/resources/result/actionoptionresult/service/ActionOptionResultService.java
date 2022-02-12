package cana.codelessautomation.api.resources.result.actionoptionresult.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.dto.UpdateActionOptionResultDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;

import java.util.List;

public interface ActionOptionResultService {
    List<ActionOptionResultDao> getActionOptionResultsByActionResultId(Long actionResultId);

    List<ErrorMessageDto> updateActionOptionResult(UpdateActionOptionResultDto updateActionOptionResultDto);
}
