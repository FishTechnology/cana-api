package cana.codelessautomation.api.resources.result.testcaseresult.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.testcaseresult.service.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.resources.result.testcaseresult.service.processors.mappers.TestCaseResultProcessorMapper;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.TestCaseResultRepository;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;

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
