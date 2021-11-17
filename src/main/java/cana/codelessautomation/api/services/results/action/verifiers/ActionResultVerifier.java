package cana.codelessautomation.api.services.results.action.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;

import java.util.List;

public interface ActionResultVerifier {
    List<ErrorMessageDto> verifyUpdateActionResult(UpdateActionResultDto updateActionResultDto);
}
