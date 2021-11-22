package cana.codelessautomation.api.resources.result.testcaseresult.mappers;

import cana.codelessautomation.api.resources.result.testcaseresult.models.TestCaseResultModel;
import cana.codelessautomation.api.resources.result.testcaseresult.models.UpdateTestCaseResultAsCompletedModel;
import cana.codelessautomation.api.services.results.testcase.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;

import java.util.List;

public interface TestCaseResultMapper {
    UpdateTestCaseResultStatusDto mapUpdateTestCaseResultStatusDto(Long testPlanResultId, Long testCaseResultId, UpdateTestCaseResultAsCompletedModel updateTestCaseResultAsCompletedModel);

    List<TestCaseResultModel> mapTestCaseResultModels(List<TestCaseResultDao> testPlanResultDao);

    TestCaseResultModel mapTestCaseResultModel(TestCaseResultDao testPlanResultDao);
}
