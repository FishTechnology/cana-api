package cana.codelessautomation.api.services.results.testplan.processors.mappers;

import cana.codelessautomation.api.services.results.testplan.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class TestPlanResultProcessorMapperImpl implements TestPlanResultProcessorMapper {
    @Override
    public TestPlanResultDao mapTestPlanResultDao(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto) {
        var testPlanResultDao = updateTestPlanResultStatusDto.getTestPlanResult();
        testPlanResultDao.setStatus(updateTestPlanResultStatusDto.getStatus());

        if (StringUtils.isNotEmpty(updateTestPlanResultStatusDto.getErrorMessage())) {
            testPlanResultDao.setErrorMessage(updateTestPlanResultStatusDto.getErrorMessage());
        }

        if (StringUtils.isNotEmpty(updateTestPlanResultStatusDto.getTotalDuration())) {
            testPlanResultDao.setTotalDuration(updateTestPlanResultStatusDto.getTotalDuration());
        }

        if (!Objects.isNull(updateTestPlanResultStatusDto.getStartedOn())) {
            testPlanResultDao.setStartedOn(updateTestPlanResultStatusDto.getStartedOn());
        }

        if (!Objects.isNull(updateTestPlanResultStatusDto.getCompletedOn())) {
            testPlanResultDao.setCompletedOn(updateTestPlanResultStatusDto.getCompletedOn());
        }

        return testPlanResultDao;
    }
}
