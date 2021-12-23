package cana.codelessautomation.api.resources.result.actionresult.service;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.actionresult.service.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.resources.result.actionresult.service.processors.ActionResultProcessor;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.resources.result.actionresult.service.verifiers.ActionResultVerifier;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
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

    @Override
    public List<ActionResultDao> getActionResultsByTestCaseResultId(Long testCaseResultId) {
        var errorMessages = actionResultVerifier.verifyGetActionResultsByTestCaseResultId(testCaseResultId);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return actionResultProcessor.processGetActionResultsByTestCaseResultId(testCaseResultId);
    }
}
