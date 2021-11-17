package cana.codelessautomation.api.services.results.testplan;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.testplan.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.services.results.testplan.processors.TestPlanResultProcessor;
import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.services.results.testplan.verifiers.TestPlanResultVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class TestPlanResultServiceImpl implements TestPlanResultService {
    @Inject
    TestPlanResultVerifier testPlanResultVerifier;

    @Inject
    TestPlanResultProcessor testPlanResultProcessor;

    @Override
    public List<ErrorMessageDto> updateTestPlanResultStatus(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto) {
        updateTestPlanResultStatusDto.setCreatedOn(OffsetDateTime.now());
        updateTestPlanResultStatusDto.setModifiedOn(OffsetDateTime.now());
        updateTestPlanResultStatusDto.setModifiedBy(updateTestPlanResultStatusDto.getModifiedBy());

        var errorMessages = testPlanResultVerifier.verifyUpdateTestPlanResultStatus(updateTestPlanResultStatusDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return testPlanResultProcessor.processUpdateTestPlanResultStatus(updateTestPlanResultStatusDto);
    }

    @Override
    public TestPlanResultDao getTestPlanResultBySchIterId(Long scheduleIterationId) {
        var errorMessages = testPlanResultVerifier.verifyGetTestPlanResultBySchIterId(scheduleIterationId);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return testPlanResultProcessor.processGetTestPlanResultBySchIterId(scheduleIterationId);
    }
}
