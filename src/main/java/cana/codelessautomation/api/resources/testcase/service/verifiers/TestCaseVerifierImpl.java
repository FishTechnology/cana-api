package cana.codelessautomation.api.resources.testcase.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.customer.service.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.resources.testcase.service.dtos.*;
import cana.codelessautomation.api.resources.testcase.service.errorcodes.TestCaseErrorCode;
import cana.codelessautomation.api.resources.testcase.service.repositories.TestCaseRepository;
import cana.codelessautomation.api.resources.testcase.service.repositories.TestplanTestcaseGroupingRepository;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;
import cana.codelessautomation.api.resources.testplan.service.verifiers.TestplanVerifier;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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
    public List<ErrorMessageDto> verifyCheckTestCaseIsDeletable(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto) {
        var errors = isTestCaseIdValid(checkTestCaseIsDeletableDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestCaseIdMapToAnyTestPlanIdValid(checkTestCaseIsDeletableDto);
    }

    @Override
    public List<ErrorMessageDto> verifyGetTestCaseById(GetTestCaseByIdDto getTestCaseByIdDto) {
        return isTestCaseIdValid(getTestCaseByIdDto);
    }

    @Override
    public List<ErrorMessageDto> verifyUpdateTestCaseById(UpdateTestCaseByIdDto updateTestCaseByIdDto) {
        var errors = isTestCaseIdValid(updateTestCaseByIdDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestCaseNameValid(updateTestCaseByIdDto);
    }

    @Override
    public List<ErrorMessageDto> isTestCaseNameValid(UpdateTestCaseByIdDto updateTestCaseByIdDto) {
        if (StringUtils.equalsAnyIgnoreCase(updateTestCaseByIdDto.getName(), updateTestCaseByIdDto.getTestCase().getName())) {
            return Collections.emptyList();
        }

        return this.isTestCaseNameValid(updateTestCaseByIdDto.getUserId(), updateTestCaseByIdDto.getName());
    }

    @Override
    public List<ErrorMessageDto> isTestCaseIdValid(UpdateTestCaseByIdDto updateTestCaseByIdDto) {
        var response = isTestCaseIdValid(updateTestCaseByIdDto.getTestCaseId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateTestCaseByIdDto.setTestCase(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestCaseIdValid(GetTestCaseByIdDto getTestCaseByIdDto) {
        var response = isTestCaseIdValid(getTestCaseByIdDto.getTestCaseId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        getTestCaseByIdDto.setTestCase(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestCaseIdMapToAnyTestPlanIdValid(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto) {
        var testplanTestcaseGroupings = testplanTestcaseGroupingRepository.findByTestCaseId(checkTestCaseIsDeletableDto.getTestCaseId());
        if (CollectionUtils.isEmpty(testplanTestcaseGroupings)) {
            return Collections.emptyList();
        }

        checkTestCaseIsDeletableDto.setTestplanTestcaseGroupings(testplanTestcaseGroupings);

        return CanaUtility.getErrorMessages(testCaseErrorCode.getTestCaseIdMappedToTestPlanIdFound());
    }

    @Override
    public List<ErrorMessageDto> isTestPlanAndTestCaseMappingValid(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto) {
//        var testplanTestcaseGroupingDao = testplanTestcaseGroupingRepository.findByTestPlanIdAndTestCaseId(checkTestCaseIsDeletableDto.getTestPlanId(), checkTestCaseIsDeletableDto.getTestCaseId());
//
//        if (testplanTestcaseGroupingDao == null) {
//            return CanaUtility.getErrorMessages(testCaseErrorCode.getTestPlanIdAndTestCaseIdNotMappedFound());
//        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestCaseIdValid(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto) {
        var response = isTestCaseIdValid(checkTestCaseIsDeletableDto.getTestCaseId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        checkTestCaseIsDeletableDto.setTestCase(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, TestCaseDao> isTestCaseIdValid(Long testCaseId) {
        KeyValue<List<ErrorMessageDto>, TestCaseDao> response = new KeyValue<>();
        var testCaseDao = testCaseRepository.findByIdAndStatus(testCaseId);
        if (testCaseDao == null) {
            response.setKey(CanaUtility.getErrorMessages(testCaseErrorCode.getTestCaseIdNotFound()));
            return response;
        }
        response.setValue(testCaseDao);
        return response;
    }

    @Override
    public List<ErrorMessageDto> isTestPlanIdValid(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto) {
//        var response = testplanVerifier.isTestplanIdValid(checkTestCaseIsDeletableDto.getTestPlanId());
//        if (CollectionUtils.isNotEmpty(response.getKey())) {
//            return response.getKey();
//        }
//        checkTestCaseIsDeletableDto.setTestplan(response.getValue());
        return Collections.emptyList();
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
