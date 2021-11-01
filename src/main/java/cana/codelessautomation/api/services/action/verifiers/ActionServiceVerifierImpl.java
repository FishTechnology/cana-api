package cana.codelessautomation.api.services.action.verifiers;

import cana.codelessautomation.api.services.action.dtos.CreateActionDto;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
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

    @Override
    public List<ErrorMessageDto> verifyCreateAction(CreateActionDto createActionDto) {
        return isTestCaseIdValid(createActionDto);
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
