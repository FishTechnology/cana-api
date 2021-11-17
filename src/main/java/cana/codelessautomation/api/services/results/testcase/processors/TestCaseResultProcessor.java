package cana.codelessautomation.api.services.results.testcase.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.testcase.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;

import java.util.List;

public interface TestCaseResultProcessor {
    List<ErrorMessageDto> processUpdateTestCaseResultStatus(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);

    List<ErrorMessageDto> updateTestCaseResultStatus(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);

    List<TestCaseResultDao> processGetTestCaseResultByPlanResultId(Long testPlanResultId);
}
