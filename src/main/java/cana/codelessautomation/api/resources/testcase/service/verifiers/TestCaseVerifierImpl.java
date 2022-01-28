package cana.codelessautomation.api.resources.testcase.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.application.service.verifiers.ApplicationVerifier;
import cana.codelessautomation.api.resources.customer.service.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.resources.testcase.service.dtos.*;
import cana.codelessautomation.api.resources.testcase.service.errorcodes.TestCaseErrorCode;
import cana.codelessautomation.api.resources.testcase.service.errorcodes.TestPlanAndTestCaseGroupErrorCode;
import cana.codelessautomation.api.resources.testcase.service.repositories.TestCaseRepository;
import cana.codelessautomation.api.resources.testcase.service.repositories.TestplanTestcaseGroupingRepository;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;
import cana.codelessautomation.api.resources.testplan.service.errorcodes.TestplanErrorCode;
import cana.codelessautomation.api.resources.testplan.service.verifiers.TestplanVerifier;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    @Inject
    TestPlanAndTestCaseGroupErrorCode testPlanAndTestCaseGroupErrorCode;

    @Inject
    ApplicationVerifier applicationVerifier;

    @Inject
    TestplanErrorCode testplanErrorCode;

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
    public List<ErrorMessageDto> verifyUpdateTestCaseOrder(UpdateTestCaseOrderDto updateTestCaseOrderDto) {
        var errors = isUserIdValid(updateTestCaseOrderDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isTestPlanIdValid(updateTestCaseOrderDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isTestPlanAndTestCaseGroupValid(updateTestCaseOrderDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestCaseIdValid(updateTestCaseOrderDto);
    }

    @Override
    public List<ErrorMessageDto> verifyDeleteTestCase(DeleteTestCaseDto deleteTestCaseDto) {
        var errors = isApplicationIdValid(deleteTestCaseDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isTestPlanIdValid(deleteTestCaseDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestCaseIdValid(deleteTestCaseDto);
    }

    @Override
    public List<ErrorMessageDto> isTestCaseIdValid(DeleteTestCaseDto deleteTestCaseDto) {
        var response = isTestCaseIdValid(deleteTestCaseDto.getTestCaseId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        deleteTestCaseDto.setTestCase(response.getValue());

        var testplanTestcaseGroupingDao = testplanTestcaseGroupingRepository.findByTestPlanIdAndTestCaseId(deleteTestCaseDto.getTestPlanId(), deleteTestCaseDto.getTestCaseId());
        if (Objects.isNull(testplanTestcaseGroupingDao)) {
            return CanaUtility.getErrorMessages(testplanErrorCode.getTestPlanIdAndTestCaseIdAreNotMapped());
        }

        deleteTestCaseDto.setTestplanTestcaseGroupingDao(testplanTestcaseGroupingDao);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestPlanIdValid(DeleteTestCaseDto deleteTestCaseDto) {
        var response = this.testplanVerifier.isTestplanIdValid(deleteTestCaseDto.getApplicationId(), deleteTestCaseDto.getTestPlanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        deleteTestCaseDto.setTestplan(response.getValue());

        if (deleteTestCaseDto.getApplicationId() != deleteTestCaseDto.getTestplan().getApplicationId()) {
            return CanaUtility.getErrorMessages(testplanErrorCode.getAppIdAndTestPlanIdAreNotMapped());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(DeleteTestCaseDto deleteTestCaseDto) {
        var response = this.applicationVerifier.isApplicationIdValid(deleteTestCaseDto.getApplicationId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        deleteTestCaseDto.setApplication(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestPlanAndTestCaseGroupValid(UpdateTestCaseOrderDto updateTestCaseOrderDto) {
        for (TestCaseOrderDto testCaseOrderDto : updateTestCaseOrderDto.getTestCaseOrderDtos()) {
            if (Objects.isNull(testCaseOrderDto.getOldExecutionOrder())) {
                continue;
            }
            var testplanTestcaseGroupingDao = testplanTestcaseGroupingRepository.findByTestPlanIdAndTestCaseId(updateTestCaseOrderDto.getTestPlanId(), testCaseOrderDto.getTestCaseId());
            if (Objects.isNull(testplanTestcaseGroupingDao)) {
                return CanaUtility.getErrorMessages(testPlanAndTestCaseGroupErrorCode.getGroupingNotFoundErrorCode());
            }
            testCaseOrderDto.setTestplanTestcaseGrouping(testplanTestcaseGroupingDao);
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestCaseIdValid(UpdateTestCaseOrderDto updateTestCaseOrderDto) {
        for (TestCaseOrderDto testCaseOrderDto : updateTestCaseOrderDto.getTestCaseOrderDtos()) {
            var response = isTestCaseIdValid(testCaseOrderDto.getTestCaseId());
            if (CollectionUtils.isNotEmpty(response.getKey())) {
                return response.getKey();
            }
            testCaseOrderDto.setTestCase(response.getValue());
        }

        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestPlanIdValid(UpdateTestCaseOrderDto updateTestCaseOrderDto) {
        var response = testplanVerifier.isTestplanIdValid(updateTestCaseOrderDto.getApplicationId(), updateTestCaseOrderDto.getTestPlanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateTestCaseOrderDto.setTestplan(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(UpdateTestCaseOrderDto updateTestCaseOrderDto) {
        var response = customerServiceVerifier.isUserIdValid(updateTestCaseOrderDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }

        updateTestCaseOrderDto.setCustomDetail(response.getValue());
        return Collections.emptyList();
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
        var response = testplanVerifier.isTestplanIdValid(getTestCaseByTestPlanIdDto.getApplicationId(), getTestCaseByTestPlanIdDto.getTestPlanId());
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
        var response = testplanVerifier.isTestplanIdValid(createTestCaseByTestPlanId.getApplicationId(), createTestCaseByTestPlanId.getTestPlanId());
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
