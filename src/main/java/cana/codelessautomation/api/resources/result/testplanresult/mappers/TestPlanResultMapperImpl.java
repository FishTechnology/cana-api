package cana.codelessautomation.api.resources.result.testplanresult.mappers;

import cana.codelessautomation.api.resources.result.testplanresult.models.TestPlanResultModel;
import cana.codelessautomation.api.resources.result.testplanresult.models.UpdateTestPlanResultAsCompletedModel;
import cana.codelessautomation.api.resources.result.testplanresult.service.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultStatusDao;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.Objects;

@ApplicationScoped
public class TestPlanResultMapperImpl implements TestPlanResultMapper {
    @Override
    public UpdateTestPlanResultStatusDto mapUpdateTestPlanResultStatusDto(Long scheduleIterationId,
                                                                          Long testPlanResultId,
                                                                          UpdateTestPlanResultAsCompletedModel updateTestPlanResultAsCompletedModel) {
        UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto = new UpdateTestPlanResultStatusDto();
        updateTestPlanResultStatusDto.setScheduleIterationId(scheduleIterationId);
        updateTestPlanResultStatusDto.setTestPlanResultId(testPlanResultId);
        updateTestPlanResultStatusDto.setStatus(EnumUtils.getEnumIgnoreCase(TestPlanResultStatusDao.class, updateTestPlanResultAsCompletedModel.getStatus()));
        updateTestPlanResultStatusDto.setErrorMessage(updateTestPlanResultAsCompletedModel.getErrorMessage());
        updateTestPlanResultStatusDto.setTotalDuration(updateTestPlanResultAsCompletedModel.getTotalDuration());

        if (StringUtils.isNotEmpty(updateTestPlanResultAsCompletedModel.getStartedOn())) {
            updateTestPlanResultStatusDto.setStartedOn(OffsetDateTime.parse(updateTestPlanResultAsCompletedModel.getStartedOn()));
        }

        if (StringUtils.isNotEmpty(updateTestPlanResultAsCompletedModel.getCompletedOn())) {
            updateTestPlanResultStatusDto.setCompletedOn(OffsetDateTime.parse(updateTestPlanResultAsCompletedModel.getCompletedOn()));
        }

        return updateTestPlanResultStatusDto;
    }

    @Override
    public TestPlanResultModel mapTestPlanResultModel(TestPlanResultDao testPlanResultDao) {
        var testPlanResultModel = new TestPlanResultModel();
        testPlanResultModel.setTestPlanId(testPlanResultDao.getTestPlanId());
        if (!Objects.isNull(testPlanResultDao.getCreatedOn())) {
            testPlanResultModel.setCreatedOn(testPlanResultDao.getCreatedOn().toString());
        }

        if (!Objects.isNull(testPlanResultDao.getCompletedOn())) {
            testPlanResultModel.setCreatedOn(testPlanResultDao.getCompletedOn().toString());
        }

        testPlanResultModel.setId(testPlanResultDao.getId());

        if (!Objects.isNull(testPlanResultDao.getModifiedOn())) {
            testPlanResultModel.setCreatedOn(testPlanResultDao.getModifiedOn().toString());
        }
        
        testPlanResultModel.setStatus(testPlanResultDao.getStatus().name());
        testPlanResultModel.setErrorMessage(testPlanResultDao.getErrorMessage());
        testPlanResultModel.setTotalDuration(testPlanResultDao.getTotalDuration());
        testPlanResultModel.setScheduleIterationId(testPlanResultDao.getScheduleIterationId());
        return testPlanResultModel;
    }
}
