package cana.codelessautomation.api.resources.action.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.action.service.dtos.*;
import cana.codelessautomation.api.resources.action.service.errorcodes.ActionErrorCode;
import cana.codelessautomation.api.resources.action.service.repositories.ActionRepository;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;
import cana.codelessautomation.api.resources.testcase.service.errorcodes.TestCaseErrorCode;
import cana.codelessautomation.api.resources.testcase.service.verifiers.TestCaseVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ActionServiceVerifierImpl implements ActionServiceVerifier {
    @Inject
    TestCaseVerifier testCaseVerifier;

    @Inject
    TestCaseErrorCode testCaseErrorCode;

    @Inject
    ActionRepository actionRepository;

    @Inject
    ActionErrorCode actionErrorCode;

    @Override
    public List<ErrorMessageDto> verifyCreateAction(CreateActionDto createActionDto) {
        return isTestCaseIdValid(createActionDto);
    }

    @Override
    public List<ErrorMessageDto> verifyGetActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto) {
        return isTestCaseIdValid(getActionsByTestCaseIdDto);
    }

    @Override
    public List<ErrorMessageDto> verifyDeleteActionById(DeleteActionByIdDto deleteActionByIdDto) {
        var errors = isTestCaseIdValid(deleteActionByIdDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isActionIdIsValid(deleteActionByIdDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestCaseIdAndActionIdMapValid(deleteActionByIdDto);
    }

    @Override
    public List<ErrorMessageDto> verifyUpdateActionOrder(UpdateActionOrderDto updateActionOrderDto) {
        var errors = isTestCaseIdValid(updateActionOrderDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isActionIdsIsValid(updateActionOrderDto);
    }

    @Override
    public List<ErrorMessageDto> isActionIdsIsValid(UpdateActionOrderDto updateActionOrderDto) {
        var actionDaos = actionRepository.findByTestCaseId(updateActionOrderDto.getTestCaseId());

        if (CollectionUtils.isEmpty(actionDaos)) {
            return CanaUtility.getErrorMessages(actionErrorCode.getActionIsEmptyForTestCaseId());
        }

        for (ActionOrderDto actionOrderDto : updateActionOrderDto.getActionOrderDtos()) {
            var actionDao = actionDaos
                    .stream()
                    .filter(x -> Objects.equals(x.getId(), actionOrderDto.getActionId()))
                    .findFirst();
            if (actionDao.isEmpty()) {
                return CanaUtility.getErrorMessages(actionErrorCode.getActionNotFound());
            }
            actionOrderDto.setActionDao(actionDao.get());
        }

        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestCaseIdValid(UpdateActionOrderDto updateActionOrderDto) {
        var response = testCaseVerifier.isTestCaseIdValid(updateActionOrderDto.getTestCaseId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return CanaUtility.getErrorMessages(testCaseErrorCode.getTestCaseIdNotFound());
        }

        updateActionOrderDto.setTestCaseDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, ActionDao> isActionIdIsValid(Long actionId) {
        KeyValue<List<ErrorMessageDto>, ActionDao> response = new KeyValue<>();
        var testCaseDao = actionRepository.findByIdAndIsActive(actionId);
        if (testCaseDao == null) {
            response.setKey(CanaUtility.getErrorMessages(testCaseErrorCode.getTestCaseIdNotFound()));
            return response;
        }
        response.setValue(testCaseDao);
        return response;
    }

    @Override
    public List<ErrorMessageDto> isTestCaseIdValid(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto) {
        var response = testCaseVerifier.isTestCaseIdValid(getActionsByTestCaseIdDto.getTestCaseId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return CanaUtility.getErrorMessages(testCaseErrorCode.getTestCaseIdNotFound());
        }

        getActionsByTestCaseIdDto.setTestCaseDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestCaseIdValid(CreateActionDto createActionDto) {
        var response = testCaseVerifier.isTestCaseIdValid(createActionDto.getTestCaseId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return CanaUtility.getErrorMessages(testCaseErrorCode.getTestCaseIdNotFound());
        }

        createActionDto.setTestCaseDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isActionIdIsValid(DeleteActionByIdDto deleteActionByIdDto) {
        var response = isActionIdIsValid(deleteActionByIdDto.getActionId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        deleteActionByIdDto.setActionDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestCaseIdValid(DeleteActionByIdDto deleteActionByIdDto) {
        var response = testCaseVerifier.isTestCaseIdValid(deleteActionByIdDto.getTestCaseId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return CanaUtility.getErrorMessages(testCaseErrorCode.getTestCaseIdNotFound());
        }

        deleteActionByIdDto.setTestCaseDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestCaseIdAndActionIdMapValid(DeleteActionByIdDto deleteActionByIdDto) {
        if (Objects.equals(deleteActionByIdDto.getActionDao().getTestCaseId(), deleteActionByIdDto.getTestCaseId())) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessages(ActionErrorCode.getTestCaseIdAndActionNotMapped());
    }
}
