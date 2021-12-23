package cana.codelessautomation.api.resources.result.testcaseresult.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.testcaseresult.service.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;

import java.util.List;

public interface TestCaseResultService {
    List<ErrorMessageDto> updateTestCaseResultStatus(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);

    List<TestCaseResultDao> getTestCaseResultByPlanResultId(Long testPlanResultId);
}
