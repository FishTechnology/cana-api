package cana.codelessautomation.api.resources.result.testcaseresult.service.processors.mappers;

import cana.codelessautomation.api.resources.result.testcaseresult.service.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultStatusDao;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class TestCaseResultProcessorMapperImpl implements TestCaseResultProcessorMapper {
    @Override
    public TestCaseResultDao mapTestCaseResultDao(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto) {
        var testCaseResultDao = updateTestCaseResultStatusDto.getTestCaseResultDao();
        testCaseResultDao.setStatus(updateTestCaseResultStatusDto.getStatus());

        if (testCaseResultDao.getStatus() == TestCaseResultStatusDao.STARTED) {
            testCaseResultDao.setStartedOn(OffsetDateTime.now());
        } else if (testCaseResultDao.getStatus() == TestCaseResultStatusDao.COMPLETED ||
                testCaseResultDao.getStatus() == TestCaseResultStatusDao.ERROR) {
            testCaseResultDao.setCompletedOn(OffsetDateTime.now());
            testCaseResultDao.setTotalDuration(updateTestCaseResultStatusDto.getTotalDuration());
        }

        testCaseResultDao.setErrorMessage(updateTestCaseResultStatusDto.getErrorMessage());
        testCaseResultDao.setModifiedOn(OffsetDateTime.now());

        return testCaseResultDao;
    }
}
