package cana.codelessautomation.api.services.results.action;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.services.results.action.processors.ActionResultProcessor;
import cana.codelessautomation.api.services.results.action.verifiers.ActionResultVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ActionResultServiceImpl implements ActionResultService {
    @Inject
    ActionResultVerifier actionResultVerifier;

    @Inject
    ActionResultProcessor actionResultProcessor;

    @Override
    public List<ErrorMessageDto> updateActionResult(UpdateActionResultDto updateActionResultDto) {
        var errorMessages = actionResultVerifier.verifyUpdateActionResult(updateActionResultDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return actionResultProcessor.processUpdateActionResult(updateActionResultDto);
    }
}
