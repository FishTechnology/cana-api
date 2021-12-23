package cana.codelessautomation.api.resources.result.testcaseresult.service.processors.mappers;

import cana.codelessautomation.api.resources.result.testcaseresult.service.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
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

        testCaseResultDao.setModifiedOn(OffsetDateTime.now());

        return testCaseResultDao;
    }
}
