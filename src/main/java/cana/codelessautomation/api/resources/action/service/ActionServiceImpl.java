package cana.codelessautomation.api.resources.action.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.action.service.dtos.CreateActionDto;
import cana.codelessautomation.api.resources.action.service.dtos.GetActionsByTestCaseIdDto;
import cana.codelessautomation.api.resources.action.service.processors.ActionServiceProcessor;
import cana.codelessautomation.api.resources.action.service.validatos.ActionServiceValidator;
import cana.codelessautomation.api.resources.action.service.verifiers.ActionServiceVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class ActionServiceImpl implements ActionService {

    @Inject
    ActionServiceVerifier actionServiceVerifier;

    @Inject
    ActionServiceProcessor actionServiceProcessor;

    @Inject
    ActionServiceValidator actionServiceValidator;

    @Override
    public List<ErrorMessageDto> createAction(CreateActionDto createActionDto) {
        createActionDto.setCreatedOn(OffsetDateTime.now());
        createActionDto.setModifiedOn(OffsetDateTime.now());
        createActionDto.setCreatedBy(createActionDto.getUserId().toString());
        createActionDto.setModifiedBy(createActionDto.getUserId().toString());
        createActionDto.setIsActive(true);

        var errorMessages = actionServiceValidator.validateCreateAction(createActionDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        errorMessages = actionServiceVerifier.verifyCreateAction(createActionDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return actionServiceProcessor.processCreateAction(createActionDto);
    }

    @Override
    public List<ErrorMessageDto> getActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto) {
        var errorMessages = actionServiceVerifier.verifyGetActionsByTestCaseId(getActionsByTestCaseIdDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return actionServiceProcessor.processGetActionsByTestCaseId(getActionsByTestCaseIdDto);
    }
}
