package cana.codelessautomation.api.services.results.action.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;

import java.util.List;

public interface ActionResultProcessor {
    List<ErrorMessageDto> processUpdateActionResult(UpdateActionResultDto updateActionResultDto);
}
