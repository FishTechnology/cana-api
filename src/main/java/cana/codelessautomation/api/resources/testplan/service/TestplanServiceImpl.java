package cana.codelessautomation.api.resources.testplan.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.testplan.service.dtos.*;
import cana.codelessautomation.api.resources.testplan.service.errorcodes.TestplanErrorCode;
import cana.codelessautomation.api.resources.testplan.service.processors.TestPlanProcessor;
import cana.codelessautomation.api.resources.testplan.service.repositories.TestPlanRepository;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestPlanStatusDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import cana.codelessautomation.api.resources.testplan.service.verifiers.TestplanVerifier;
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
        createTestplan.setCreatedBy(createTestplan.getUserId().toString());
        createTestplan.setModifiedBy(createTestplan.getUserId().toString());
        createTestplan.setStatus(TestPlanStatusDao.SETUP);
        var errors = testplanVerifier.verifyCreateTestplan(createTestplan);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }

        return testPlanProcessor.processorCreateTestplan(createTestplan);
    }

    @Override
    public List<TestplanDao> getTestplans(Long applicationId) throws ValidationException {
        var errors = testplanVerifier.verifyGetTestplans(applicationId);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }

        return testPlanProcessor.processorGetTestplans(applicationId);
    }

    @Override
    public TestplanDao getTestplanById(Long applicationId, Long testplanId) throws ValidationException {
        var testplanDao = testPlanRepository.findByIdAndStatus(applicationId, testplanId);
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
        updateTestplanStatus.setCreatedBy(updateTestplanStatus.getUserId().toString());
        updateTestplanStatus.setModifiedBy(updateTestplanStatus.getUserId().toString());

        var errors = testplanVerifier.verifyUpdateTestplanStatus(updateTestplanStatus);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }

        return testPlanProcessor.processorUpdateTestplanStatus(updateTestplanStatus);
    }

    @Override
    public List<ErrorMessageDto> copyTestplan(CopyTestPlanDto copyTestPlanDto) {
        copyTestPlanDto.setStatus(TestPlanStatusDao.SETUP);
        var errors = testplanVerifier.verifyCopyTestplan(copyTestPlanDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }

        return testPlanProcessor.processorCopyTestplan(copyTestPlanDto);
    }
}
