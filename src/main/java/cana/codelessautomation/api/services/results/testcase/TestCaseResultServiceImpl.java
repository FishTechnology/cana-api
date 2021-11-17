package cana.codelessautomation.api.services.results.testcase;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.testcase.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.services.results.testcase.processors.TestCaseResultProcessor;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.services.results.testcase.verifiers.TestCaseResultVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TestCaseResultServiceImpl implements TestCaseResultService {

    @Inject
    TestCaseResultVerifier testCaseResultVerifier;

    @Inject
    TestCaseResultProcessor testCaseResultProcessor;

    @Override
    public List<ErrorMessageDto> updateTestCaseResultStatus(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto) {
        var errorMessages = testCaseResultVerifier.verifyUpdateTestCaseResultStatus(updateTestCaseResultStatusDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return testCaseResultProcessor.processUpdateTestCaseResultStatus(updateTestCaseResultStatusDto);
    }

    @Override
    public List<TestCaseResultDao> getTestCaseResultByPlanResultId(Long testPlanResultId) {
        var errorMessages = testCaseResultVerifier.verifyGetTestCaseResultByPlanResultId(testPlanResultId);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return testCaseResultProcessor.processGetTestCaseResultByPlanResultId(testPlanResultId);
    }
}
