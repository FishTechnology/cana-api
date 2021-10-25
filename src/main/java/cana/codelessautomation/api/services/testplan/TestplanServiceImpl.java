package cana.codelessautomation.api.services.testplan;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.testplan.dtos.CreateTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.DeleteTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.UpdateTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.UpdateTestplanStatusDto;
import cana.codelessautomation.api.services.testplan.errorcodes.TestplanErrorCode;
import cana.codelessautomation.api.services.testplan.processors.TestPlanProcessor;
import cana.codelessautomation.api.services.testplan.repositories.TestPlanRepository;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestPlanStatus;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;
import cana.codelessautomation.api.services.testplan.verifiers.TestplanVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class TestplanServiceImpl implements TestplanService {

    @Inject
    TestplanVerifier testplanVerifier;

    @Inject
    TestPlanProcessor testPlanProcessor;

    @Inject
    TestPlanRepository testPlanRepository;

    @Inject
    TestplanErrorCode testplanErrorCode;

    @Override
    public List<ErrorMessageDto> createTestplan(CreateTestplanDto createTestplan) throws ValidationException {
        createTestplan.setCreatedOn(OffsetDateTime.now());
        createTestplan.setModifiedOn(OffsetDateTime.now());
        createTestplan.setCreatedBy(createTestplan.getUserId().toString());
        createTestplan.setModifiedBy(createTestplan.getUserId().toString());
        createTestplan.setStatus(TestPlanStatus.SETUP);
        var errors = testplanVerifier.verifyCreateTestplan(createTestplan);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }

        return testPlanProcessor.processorCreateTestplan(createTestplan);
    }

    @Override
    public List<TestplanDao> getTestplans(Long userId) throws ValidationException {
        var errors = testplanVerifier.verifyGetTestplans(userId);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }

        return testPlanProcessor.processorGetTestplans(userId);
    }

    @Override
    public TestplanDao getTestplanById(Long testplanId) throws ValidationException {
        var testplanDao = testPlanRepository.findByIdAndStatus(testplanId);
        if (testplanDao == null) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(testplanErrorCode.getTestPlanIdNotFound()));
        }

        return testplanDao;
    }

    @Override
    public List<ErrorMessageDto> deleteTestplan(DeleteTestplanDto deleteTestplan) throws ValidationException {
        var errors = testplanVerifier.verifyDeleteTestplan(deleteTestplan);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }

        return testPlanProcessor.processorDeleteTestplan(deleteTestplan);
    }

    @Override
    public List<ErrorMessageDto> updateTestplan(UpdateTestplanDto updateTestplanModel) throws ValidationException {
        updateTestplanModel.setCreatedOn(OffsetDateTime.now());
        updateTestplanModel.setModifiedOn(OffsetDateTime.now());
        updateTestplanModel.setCreatedBy(updateTestplanModel.getUserId().toString());
        updateTestplanModel.setModifiedBy(updateTestplanModel.getUserId().toString());

        var errors = testplanVerifier.verifyUpdateTestplan(updateTestplanModel);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }

        return testPlanProcessor.processorUpdateTestplan(updateTestplanModel);
    }

    @Override
    public List<ErrorMessageDto> updateTestplanStatus(UpdateTestplanStatusDto updateTestplanStatus) throws ValidationException {
        updateTestplanStatus.setCreatedOn(OffsetDateTime.now());
        updateTestplanStatus.setModifiedOn(OffsetDateTime.now());
        updateTestplanStatus.setCreatedBy(updateTestplanStatus.getUserId().toString());
        updateTestplanStatus.setModifiedBy(updateTestplanStatus.getUserId().toString());

        var errors = testplanVerifier.verifyUpdateTestplanStatus(updateTestplanStatus);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }

        return testPlanProcessor.processorUpdateTestplanStatus(updateTestplanStatus);
    }
}
