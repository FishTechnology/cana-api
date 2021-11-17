package cana.codelessautomation.api.resources.result.testplanresult.mappers;

import cana.codelessautomation.api.resources.result.testplanresult.models.TestPlanResultModel;
import cana.codelessautomation.api.resources.result.testplanresult.models.UpdateTestPlanResultAsCompletedModel;
import cana.codelessautomation.api.services.results.testplan.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;

public interface TestPlanResultMapper {
    UpdateTestPlanResultStatusDto mapUpdateTestPlanResultStatusDto(Long scheduleIterationId, Long testPlanResultId, UpdateTestPlanResultAsCompletedModel updateTestPlanResultAsCompletedModel);

    TestPlanResultModel mapTestPlanResultModel(TestPlanResultDao testPlanResultDao);
}
