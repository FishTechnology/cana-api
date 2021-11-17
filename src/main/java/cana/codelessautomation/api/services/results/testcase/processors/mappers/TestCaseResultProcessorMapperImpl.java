package cana.codelessautomation.api.services.results.testcase.processors.mappers;

import cana.codelessautomation.api.services.results.testcase.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class TestCaseResultProcessorMapperImpl implements TestCaseResultProcessorMapper {
    @Override
    public TestCaseResultDao mapTestCaseResultDao(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto) {
        var testCaseResultDao = updateTestCaseResultStatusDto.getTestCaseResultDao();
        testCaseResultDao.setStatus(updateTestCaseResultStatusDto.getStatus());

        if (!Objects.isNull(updateTestCaseResultStatusDto.getCompletedOn())) {
            testCaseResultDao.setCompletedOn(updateTestCaseResultStatusDto.getCompletedOn());
        }

        if (!Objects.isNull(updateTestCaseResultStatusDto.getStartedOn())) {
            testCaseResultDao.setStartedOn(updateTestCaseResultStatusDto.getStartedOn());
        }

        if (StringUtils.isNotEmpty(updateTestCaseResultStatusDto.getTotalDuration())) {
            testCaseResultDao.setTotalDuration(updateTestCaseResultStatusDto.getTotalDuration());
        }

        if (StringUtils.isNotEmpty(updateTestCaseResultStatusDto.getErrorMessage())) {
            testCaseResultDao.setErrorMessage(updateTestCaseResultStatusDto.getErrorMessage());
        }

        testCaseResultDao.setModifiedOn(updateTestCaseResultStatusDto.getModifiedOn());

        return testCaseResultDao;
    }
}
