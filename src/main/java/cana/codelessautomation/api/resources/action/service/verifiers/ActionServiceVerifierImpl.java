package cana.codelessautomation.api.resources.action.service.verifiers;

import cana.codelessautomation.api.resources.action.service.dtos.CreateActionDto;
import cana.codelessautomation.api.resources.action.service.dtos.GetActionsByTestCaseIdDto;
import cana.codelessautomation.api.resources.action.service.repositories.ActionRepository;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.testcase.service.errorcodes.TestCaseErrorCode;
import cana.codelessautomation.api.resources.testcase.service.verifiers.TestCaseVerifier;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ActionServiceVerifierImpl implements ActionServiceVerifier {
    @Inject
    TestCaseVerifier testCaseVerifier;

    @Inject
    TestCaseErrorCode testCaseErrorCode;

    @Inject
    ActionRepository actionRepository;

    @Override
    public List<ErrorMessageDto> verifyCreateAction(CreateActionDto createActionDto) {
        return isTestCaseIdValid(createActionDto);
    }

    @Override
    public List<ErrorMessageDto> verifyGetActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto) {
        return isTestCaseIdValid(getActionsByTestCaseIdDto);
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
}
