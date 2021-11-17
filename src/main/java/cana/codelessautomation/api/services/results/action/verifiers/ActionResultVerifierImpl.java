package cana.codelessautomation.api.services.results.action.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ActionResultVerifierImpl implements ActionResultVerifier {
    @Override
    public List<ErrorMessageDto> verifyUpdateActionResult(UpdateActionResultDto updateActionResultDto) {
        return null;
    }
}
