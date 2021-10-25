package cana.codelessautomation.api.services.testcase.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.customer.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseDto;
import cana.codelessautomation.api.services.testcase.dtos.GetTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.errorcodes.TestCaseErrorCode;
import cana.codelessautomation.api.services.testcase.repositories.TestCaseRepository;
import cana.codelessautomation.api.services.testcase.repositories.TestplanTestcaseGroupingRepository;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestplanTestcaseGroupingDao;
import cana.codelessautomation.api.services.testplan.verifiers.TestplanVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TestCaseVerifierImpl implements TestCaseVerifier {
    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Inject
    TestCaseRepository testCaseRepository;

    @Inject
    TestCaseErrorCode testCaseErrorCode;

    @Inject
    TestplanVerifier testplanVerifier;

    @Inject
    TestplanTestcaseGroupingRepository testplanTestcaseGroupingRepository;

    @Override
    public List<ErrorMessageDto> verifyCreateTestCase(CreateTestCaseDto createTestCase) {
        var errors = isUserIdValid(createTestCase);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestCaseNameValid(createTestCase);
    }

    @Override
    public List<ErrorMessageDto> verifyCreateTestCaseByPlanId(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) {
        var errors = isUserIdValid(createTestCaseByTestPlanId);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isTestPlanIdValidId(createTestCaseByTestPlanId);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestCaseNameByTestPlanIdValid(createTestCaseByTestPlanId);
    }

    @Override
    public List<ErrorMessageDto> verifyGetTestCaseByUserId(Long userId) {
        return isUserIdValid(userId);
    }

    @Override
    public List<ErrorMessageDto> verifyGetTestCaseByTestPlanId(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto) {
        return isTestPlanIdValid(getTestCaseByTestPlanIdDto);
    }

    @Override
    public List<ErrorMessageDto> isTestPlanIdValid(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto) {
        var response = testplanVerifier.isTestplanIdValid(getTestCaseByTestPlanIdDto.getTestPlanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        getTestCaseByTestPlanIdDto.setTestplanDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(Long userId) {
        var response = customerServiceVerifier.isUserIdValid(userId);
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestPlanIdValidId(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) {
        var response = testplanVerifier.isTestplanIdValid(createTestCaseByTestPlanId.getTestPlanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        createTestCaseByTestPlanId.setTestplan(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestCaseNameByTestPlanIdValid(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) {
        var testplanTestcaseGroupingDaos = testplanTestcaseGroupingRepository.findByTestPlanId(createTestCaseByTestPlanId.getTestPlanId());
        if (CollectionUtils.isEmpty(testplanTestcaseGroupingDaos)) {
            return Collections.emptyList();
        }
        List<Long> testCaseIds = new ArrayList<>();

        for (TestplanTestcaseGroupingDao testplanTestcaseGroupingDao : testplanTestcaseGroupingDaos) {
            testCaseIds.add(testplanTestcaseGroupingDao.getTestCaseId());
        }
        return checkTestCaseNameValid(testCaseIds, createTestCaseByTestPlanId.getName());
    }

    @Override
    public List<ErrorMessageDto> checkTestCaseNameValid(List<Long> testCaseIds, String testCaseName) {
        var testCaseCount = testCaseRepository.count("id IN ( ?1 ) and lower(name) = lower( ?2 )", testCaseIds, testCaseName);

        if (testCaseCount >= 1) {
            return CanaUtility.getErrorMessages(testCaseErrorCode.getTestCaseNameDuplicate());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) {
        var response = customerServiceVerifier.isUserIdValid(createTestCaseByTestPlanId.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createTestCaseByTestPlanId.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestCaseNameValid(CreateTestCaseDto createTestCase) {
        return this.isTestCaseNameValid(createTestCase.getUserId(), createTestCase.getName());
    }

    @Override
    public List<ErrorMessageDto> isTestCaseNameValid(Long userId, String testCaseName) {
        long testCaseCount = testCaseRepository.count("userid = ?1 and isactive=true and name = ?2", userId, testCaseName);
        if (testCaseCount >= 1) {
            return CanaUtility.getErrorMessages(testCaseErrorCode.getTestCaseNameDuplicate());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(CreateTestCaseDto createTestCase) {
        var response = customerServiceVerifier.isUserIdValid(createTestCase.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createTestCase.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }
}
