package cana.codelessautomation.api.resources.result.testcaseresult.mappers;

import cana.codelessautomation.api.resources.result.testcaseresult.models.TestCaseResultModel;
import cana.codelessautomation.api.resources.result.testcaseresult.models.UpdateTestCaseResultModel;
import cana.codelessautomation.api.resources.result.testcaseresult.service.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;

import java.util.List;

public interface TestCaseResultMapper {
    UpdateTestCaseResultStatusDto mapUpdateTestCaseResultStatusDto(Long testPlanResultId, Long testCaseResultId, UpdateTestCaseResultModel updateTestCaseResultAsCompletedModel);

    List<TestCaseResultModel> mapTestCaseResultModels(List<TestCaseResultDao> testPlanResultDao);

    TestCaseResultModel mapTestCaseResultModel(TestCaseResultDao testPlanResultDao);
}
