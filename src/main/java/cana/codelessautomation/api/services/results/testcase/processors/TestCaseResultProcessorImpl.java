package cana.codelessautomation.api.services.results.testcase.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.testcase.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.services.results.testcase.processors.mappers.TestCaseResultProcessorMapper;
import cana.codelessautomation.api.services.results.testcase.repositories.TestCaseResultRepository;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TestCaseResultProcessorImpl implements TestCaseResultProcessor {

    @Inject
    TestCaseResultRepository testCaseResultRepository;

    @Inject
    TestCaseResultProcessorMapper testCaseResultProcessorMapper;

    @Override
    public List<ErrorMessageDto> processUpdateTestCaseResultStatus(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto) {
        return updateTestCaseResultStatus(updateTestCaseResultStatusDto);
    }

    @Override
    public List<ErrorMessageDto> updateTestCaseResultStatus(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto) {
        var testCaseResultDao = testCaseResultProcessorMapper.mapTestCaseResultDao(updateTestCaseResultStatusDto);
        testCaseResultRepository.persist(testCaseResultDao);
        return Collections.emptyList();
    }

    @Override
    public List<TestCaseResultDao> processGetTestCaseResultByPlanResultId(Long testPlanResultId) {
        return testCaseResultRepository.findByPlanResultId(testPlanResultId);
    }
}
