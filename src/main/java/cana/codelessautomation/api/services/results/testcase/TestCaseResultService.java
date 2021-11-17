package cana.codelessautomation.api.services.results.testcase;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.testcase.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;

import java.util.List;

public interface TestCaseResultService {
    List<ErrorMessageDto> updateTestCaseResultStatus(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);

    List<TestCaseResultDao> getTestCaseResultByPlanResultId(Long testPlanResultId);
}
