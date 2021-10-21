package cana.codelessautomation.api.services.testplan.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.customer.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.services.environment.repositories.daos.TestPlanStatus;
import cana.codelessautomation.api.services.testplan.dtos.CreateTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.DeleteTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.UpdateTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.UpdateTestplanStatusDto;
import cana.codelessautomation.api.services.testplan.errorcodes.TestplanErrorCode;
import cana.codelessautomation.api.services.testplan.repositories.TestPlanRepository;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;
import cana.codelessautomation.api.services.utilities.CanaUtility;
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

    @Override
    public List<ErrorMessageDto> verifyCreateTestplan(CreateTestplanDto createTestplan) {
        var errors = isUserIdValid(createTestplan);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestplanNameValid(createTestplan);
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
    public List<ErrorMessageDto> verifyGetTestplans(Long userId) {
        return isUserIdValid(userId);
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
    public List<ErrorMessageDto> verifyDeleteTestplan(DeleteTestplanDto deleteTestplan) {
        return isTestplanIdValid(deleteTestplan);
    }

    @Override
    public List<ErrorMessageDto> isTestplanIdValid(DeleteTestplanDto deleteTestplan) {
        var response = isTestplanIdValid(deleteTestplan.getTestplanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        deleteTestplan.setTestplan(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, TestplanDao> isTestplanIdValid(Long testplanId) {
        KeyValue<List<ErrorMessageDto>, TestplanDao> response = new KeyValue<>();
        var testplanDao = testPlanRepository.findByIdAndStatus(testplanId);
        if (testplanDao == null) {
            response.setKey(CanaUtility.getErrorMessages(testplanErrorCode.getTestPlanIdNotFound()));
            return response;
        }
        response.setValue(testplanDao);
        return response;
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
        var response = isTestplanIdValid(updateTestplan.getTestplanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateTestplan.setTestplan(response.getValue());
        return Collections.emptyList();
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
    public List<ErrorMessageDto> isTestplanStatusValid(UpdateTestplanStatusDto updateTestplanStatus) {
        if (updateTestplanStatus.getTestplan().getStatus() == TestPlanStatus.DELETED) {
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
        var response = isTestplanIdValid(updateTestplanStatus.getTestplanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateTestplanStatus.setTestplan(response.getValue());
        return Collections.emptyList();
    }
}
