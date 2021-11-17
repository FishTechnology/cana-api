package cana.codelessautomation.api.services.results.testplan.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.testplan.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;

import java.util.List;

public interface TestPlanResultProcessor {
    List<ErrorMessageDto> processUpdateTestPlanResultStatus(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);

    List<ErrorMessageDto> updateTestPlanResultStatusDto(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);

    TestPlanResultDao processGetTestPlanResultBySchIterId(Long scheduleIterationId);

    TestPlanResultDao getTestPlanResultBySchIterId(Long scheduleIterationId);
}
