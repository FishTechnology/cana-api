package cana.codelessautomation.api.services.action.verifiers;

import cana.codelessautomation.api.services.action.dtos.CreateActionDto;
import cana.codelessautomation.api.services.action.dtos.GetActionsByTestCaseIdDto;
import cana.codelessautomation.api.services.action.repositories.ActionRepository;
import cana.codelessautomation.api.services.action.repositories.daos.ActionDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.testcase.errorcodes.TestCaseErrorCode;
import cana.codelessautomation.api.services.testcase.verifiers.TestCaseVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
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
