package cana.codelessautomation.api.resources.result.testplanresult.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.testplanresult.service.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;

import java.util.List;

public interface TestPlanResultService {
    List<ErrorMessageDto> updateTestPlanResultStatus(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);

    TestPlanResultDao getTestPlanResultBySchIterId(Long scheduleIterationId);
}
