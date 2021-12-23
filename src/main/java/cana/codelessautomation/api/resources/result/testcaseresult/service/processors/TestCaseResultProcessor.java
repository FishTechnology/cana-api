package cana.codelessautomation.api.resources.result.testcaseresult.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.testcaseresult.service.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;

import java.util.List;

public interface TestCaseResultProcessor {
    List<ErrorMessageDto> processUpdateTestCaseResultStatus(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);

    List<ErrorMessageDto> updateTestCaseResultStatus(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);

    List<TestCaseResultDao> processGetTestCaseResultByPlanResultId(Long testPlanResultId);
}
