package cana.codelessautomation.api.services.results.testplan;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.testplan.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;

import java.util.List;

public interface TestPlanResultService {
    List<ErrorMessageDto> updateTestPlanResultStatus(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);

    TestPlanResultDao getTestPlanResultBySchIterId(Long scheduleIterationId);
}
