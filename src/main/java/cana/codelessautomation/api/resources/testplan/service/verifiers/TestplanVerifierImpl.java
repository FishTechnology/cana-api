package cana.codelessautomation.api.resources.testplan.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.application.service.verifiers.ApplicationVerifier;
import cana.codelessautomation.api.resources.customer.service.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.resources.testplan.service.dtos.*;
import cana.codelessautomation.api.resources.testplan.service.errorcodes.TestplanErrorCode;
import cana.codelessautomation.api.resources.testplan.service.repositories.TestPlanRepository;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestPlanStatusDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TestplanVerifierImpl implements TestplanVerifier {

    @Inject
    TestPlanRepository testPlanRepository;

    @Inject
    TestplanErrorCode testplanErrorCode;

    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Inject
    ApplicationVerifier applicationVerifier;

    @Override
    public List<ErrorMessageDto> verifyCreateTestplan(CreateTestplanDto createTestplan) {
        var errors = isUserIdValid(createTestplan);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isApplicationIdValid(createTestplan);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestplanNameValid(createTestplan);
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(CreateTestplanDto createTestplan) {
        var response = applicationVerifier.isApplicationIdValid(createTestplan.getApplicationId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        createTestplan.setApplication(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyCopyTestplan(CopyTestPlanDto copyTestPlanDto) {
        var errors = isUserIdValid(copyTestPlanDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }

        errors = isTestplanIdValid(copyTestPlanDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }

        return isTestplanNameValid(copyTestPlanDto);
    }

    @Override
    public List<ErrorMessageDto> verifyGetTestplans(Long applicationId) {
        return isApplicationIdValid(applicationId);
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(Long applicationId) {
        var response = applicationVerifier.isApplicationIdValid(applicationId);
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyDeleteTestplan(DeleteTestplanDto deleteTestplan) {
        return isTestplanIdValid(deleteTestplan);
    }

    @Override
    public List<ErrorMessageDto> verifyUpdateTestplan(UpdateTestplanDto updateTestplan) {
        var errors = isTestplanIdValid(updateTestplan);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestplanNameValid(updateTestplan);
    }

    @Override
    public List<ErrorMessageDto> verifyUpdateTestplanStatus(UpdateTestplanStatusDto updateTestplanStatus) {
        var errors = isTestplanIdValid(updateTestplanStatus);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isUserIdValid(updateTestplanStatus);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestplanStatusValid(updateTestplanStatus);
    }

    @Override
    public List<ErrorMessageDto> isTestplanNameValid(CopyTestPlanDto copyTestPlanDto) {
        var testplanDao = testPlanRepository.findByNameAndUserId(copyTestPlanDto.getUserId(), copyTestPlanDto.getTestPlanName());
        if (testplanDao != null) {
            return CanaUtility.getErrorMessages(testplanErrorCode.getTestPlanNameIsDuplicate());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestplanIdValid(CopyTestPlanDto copyTestPlanDto) {
        var response = isTestplanIdValid(copyTestPlanDto.getApplicationId(), copyTestPlanDto.getTestPlanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        copyTestPlanDto.setTestplan(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(CopyTestPlanDto copyTestPlanDto) {
        var response = customerServiceVerifier.isUserIdValid(copyTestPlanDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        copyTestPlanDto.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestplanNameValid(CreateTestplanDto createTestplan) {
        var testplanDao = testPlanRepository.findByNameAndUserId(createTestplan.getUserId(), createTestplan.getName());
        if (testplanDao != null) {
            return CanaUtility.getErrorMessages(testplanErrorCode.getTestPlanNameIsDuplicate());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(CreateTestplanDto createTestplan) {
        var response = customerServiceVerifier.isUserIdValid(createTestplan.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createTestplan.setCustomDetail(response.getValue());
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
    public List<ErrorMessageDto> isTestplanIdValid(DeleteTestplanDto deleteTestplan) {
        var response = isTestplanIdValid(deleteTestplan.getApplicationId(), deleteTestplan.getTestplanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        deleteTestplan.setTestplan(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, TestplanDao> isTestplanIdValid(Long applicationId, Long testplanId) {
        KeyValue<List<ErrorMessageDto>, TestplanDao> response = new KeyValue<>();
        var testplanDao = testPlanRepository.findByIdAndStatus(applicationId, testplanId);
        if (testplanDao == null) {
            response.setKey(CanaUtility.getErrorMessages(testplanErrorCode.getTestPlanIdNotFound()));
            return response;
        }
        response.setValue(testplanDao);
        return response;
    }

    @Override
    public List<ErrorMessageDto> isTestplanNameValid(UpdateTestplanDto updateTestplan) {
        if (StringUtils.equalsAnyIgnoreCase(updateTestplan.getName(), updateTestplan.getName())) {
            return Collections.emptyList();
        }

        var testplanDao = testPlanRepository.findByNameAndUserId(updateTestplan.getUserId(), updateTestplan.getName());
        if (testplanDao != null) {
            return CanaUtility.getErrorMessages(testplanErrorCode.getTestPlanNameIsDuplicate());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestplanIdValid(UpdateTestplanDto updateTestplan) {
        var response = isTestplanIdValid(updateTestplan.getApplicationId(), updateTestplan.getTestplanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateTestplan.setTestplan(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestplanStatusValid(UpdateTestplanStatusDto updateTestplanStatus) {
        if (updateTestplanStatus.getTestplan().getStatus() == TestPlanStatusDao.DELETED) {
            return CanaUtility.getErrorMessages(testplanErrorCode.getTestPlanIsInActive());
        }

        if (updateTestplanStatus.getTestplan().getStatus() == updateTestplanStatus.getStatus()) {
            return CanaUtility.getErrorMessages(testplanErrorCode.getTestPlanIsInSameStatus());
        }

        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(UpdateTestplanStatusDto updateTestplanStatus) {
        var response = customerServiceVerifier.isUserIdValid(updateTestplanStatus.getUserId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }

        updateTestplanStatus.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestplanIdValid(UpdateTestplanStatusDto updateTestplanStatus) {
        var response = isTestplanIdValid(updateTestplanStatus.getApplicationId(), updateTestplanStatus.getTestplanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateTestplanStatus.setTestplan(response.getValue());
        return Collections.emptyList();
    }
}
