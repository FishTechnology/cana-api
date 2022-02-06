package cana.codelessautomation.api.resources.result.testplanresult.service.processors.mappers;

import cana.codelessautomation.api.resources.result.testplanresult.service.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultStatusDao;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class TestPlanResultProcessorMapperImpl implements TestPlanResultProcessorMapper {
    @Override
    public TestPlanResultDao mapTestPlanResultDao(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto) {
        var testPlanResultDao = updateTestPlanResultStatusDto.getTestPlanResult();
        testPlanResultDao.setStatus(updateTestPlanResultStatusDto.getStatus());

        if (updateTestPlanResultStatusDto.getStatus() == TestPlanResultStatusDao.STARTED) {
            testPlanResultDao.setStartedOn(OffsetDateTime.now());
        } else if (updateTestPlanResultStatusDto.getStatus() == TestPlanResultStatusDao.COMPLETED) {
            if (StringUtils.isNotEmpty(updateTestPlanResultStatusDto.getTotalDuration())) {
                testPlanResultDao.setTotalDuration(updateTestPlanResultStatusDto.getTotalDuration());
            }
            testPlanResultDao.setCompletedOn(OffsetDateTime.now());
        }

        if (StringUtils.isNotEmpty(updateTestPlanResultStatusDto.getErrorMessage())) {
            testPlanResultDao.setErrorMessage(updateTestPlanResultStatusDto.getErrorMessage());
        }

        return testPlanResultDao;
    }
}
