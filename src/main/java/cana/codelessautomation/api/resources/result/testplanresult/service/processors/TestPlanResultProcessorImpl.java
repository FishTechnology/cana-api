package cana.codelessautomation.api.resources.result.testplanresult.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.testplanresult.service.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.resources.result.testplanresult.service.processors.mappers.TestPlanResultProcessorMapper;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.TestPlanResultRepository;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TestPlanResultProcessorImpl implements TestPlanResultProcessor {

    @Inject
    TestPlanResultRepository testPlanResultRepository;

    @Inject
    TestPlanResultProcessorMapper testPlanResultProcessorMapper;

    @Override
    public List<ErrorMessageDto> processUpdateTestPlanResultStatus(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto) {
        return updateTestPlanResultStatusDto(updateTestPlanResultStatusDto);
    }

    @Override
    public List<ErrorMessageDto> updateTestPlanResultStatusDto(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto) {
        var testPlanResultDao = testPlanResultProcessorMapper.mapTestPlanResultDao(updateTestPlanResultStatusDto);
        testPlanResultRepository.persist(testPlanResultDao);
        return Collections.emptyList();
    }

    @Override
    public TestPlanResultDao processGetTestPlanResultBySchIterId(Long scheduleIterationId) {
        return getTestPlanResultBySchIterId(scheduleIterationId);
    }

    @Override
    public TestPlanResultDao getTestPlanResultBySchIterId(Long scheduleIterationId) {
        return testPlanResultRepository.findByScheduleIterationId(scheduleIterationId);
    }
}
