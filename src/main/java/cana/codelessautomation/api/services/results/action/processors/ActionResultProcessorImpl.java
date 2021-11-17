package cana.codelessautomation.api.services.results.action.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ActionResultProcessorImpl implements ActionResultProcessor {
    @Override
    public List<ErrorMessageDto> processUpdateActionResult(UpdateActionResultDto updateActionResultDto) {
        return null;
    }
}
