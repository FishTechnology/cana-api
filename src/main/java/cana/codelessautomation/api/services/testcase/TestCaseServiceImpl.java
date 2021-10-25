package cana.codelessautomation.api.services.testcase;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseDto;
import cana.codelessautomation.api.services.testcase.dtos.GetTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.processors.TestCaseProcessor;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.services.testcase.verifiers.TestCaseVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class TestCaseServiceImpl implements TestCaseService {
    @Inject
    TestCaseProcessor testCaseProcessor;

    @Inject
    TestCaseVerifier testCaseVerifier;


    @Override
    public List<ErrorMessageDto> createTestCase(CreateTestCaseDto createTestCase) throws ValidationException {
        createTestCase.setCreatedOn(OffsetDateTime.now());
        createTestCase.setModifiedOn(OffsetDateTime.now());
        createTestCase.setCreatedBy(createTestCase.getUserId().toString());
        createTestCase.setModifiedBy(createTestCase.getUserId().toString());
        createTestCase.setIsActive(true);

        var errorMessages = testCaseVerifier.verifyCreateTestCase(createTestCase);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return testCaseProcessor.processCreateTestCase(createTestCase);
    }

    @Override
    public List<ErrorMessageDto> createTestCaseByPlanId(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) throws ValidationException {
        createTestCaseByTestPlanId.setCreatedOn(OffsetDateTime.now());
        createTestCaseByTestPlanId.setModifiedOn(OffsetDateTime.now());
        createTestCaseByTestPlanId.setCreatedBy(createTestCaseByTestPlanId.getUserId().toString());
        createTestCaseByTestPlanId.setModifiedBy(createTestCaseByTestPlanId.getUserId().toString());
        createTestCaseByTestPlanId.setIsActive(true);

        var errorMessages = testCaseVerifier.verifyCreateTestCaseByPlanId(createTestCaseByTestPlanId);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return testCaseProcessor.processCreateTestCaseByPlanId(createTestCaseByTestPlanId);
    }

    @Override
    public List<TestCaseDao> getTestCaseByUserId(Long userId) throws ValidationException {
        var errorMessages = testCaseVerifier.verifyGetTestCaseByUserId(userId);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return testCaseProcessor.processGetTestCaseByUserId(userId);
    }

    @Override
    public List<ErrorMessageDto> getTestCaseByTestPlanId(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto) throws ValidationException {
        var errorMessages = testCaseVerifier.verifyGetTestCaseByTestPlanId(getTestCaseByTestPlanIdDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return testCaseProcessor.processGetTestCaseByTestPlanId(getTestCaseByTestPlanIdDto);
    }
}
