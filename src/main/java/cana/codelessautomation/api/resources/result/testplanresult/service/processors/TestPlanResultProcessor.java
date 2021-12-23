package cana.codelessautomation.api.resources.result.testplanresult.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.testplanresult.service.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;

import java.util.List;

public interface TestPlanResultProcessor {
    List<ErrorMessageDto> processUpdateTestPlanResultStatus(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);

    List<ErrorMessageDto> updateTestPlanResultStatusDto(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);

    TestPlanResultDao processGetTestPlanResultBySchIterId(Long scheduleIterationId);

    TestPlanResultDao getTestPlanResultBySchIterId(Long scheduleIterationId);
}
