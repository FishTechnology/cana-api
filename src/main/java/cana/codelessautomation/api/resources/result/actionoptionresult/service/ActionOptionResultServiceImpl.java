package cana.codelessautomation.api.resources.result.actionoptionresult.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.dto.UpdateActionOptionResultDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.processors.ActionOptionResultProcessor;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.verifiers.ActionOptionResultVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ActionOptionResultServiceImpl implements ActionOptionResultService {

    @Inject
    ActionOptionResultVerifier actionOptionResultVerifier;

    @Inject
    ActionOptionResultProcessor actionOptionResultProcessor;

    @Override
    public List<ActionOptionResultDao> getActionOptionResultsByActionResultId(Long actionResultId) {
        var errorMessages = actionOptionResultVerifier.verifyGetActionResultsByTestCaseResultId(actionResultId);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return actionOptionResultProcessor.processGetActionResultsByTestCaseResultId(actionResultId);
    }

    @Override
    public List<ErrorMessageDto> updateActionOptionResult(UpdateActionOptionResultDto updateActionOptionResultDto) {
        var errorMessages = actionOptionResultVerifier.verifyUpdateActionOptionResult(updateActionOptionResultDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return actionOptionResultProcessor.processUpdateActionOptionResult(updateActionOptionResultDto);
    }
}
