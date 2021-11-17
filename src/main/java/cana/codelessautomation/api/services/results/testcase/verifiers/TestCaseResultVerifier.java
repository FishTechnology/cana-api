package cana.codelessautomation.api.services.results.testcase.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.results.testcase.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;

import java.util.List;

public interface TestCaseResultVerifier {
    List<ErrorMessageDto> verifyUpdateTestCaseResultStatus(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);

    List<ErrorMessageDto> isTestCaseResultIdValid(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);

    KeyValue<List<ErrorMessageDto>, TestCaseResultDao> isTestCaseResultIdValid(Long testCaseResultId);

    List<ErrorMessageDto> isTestPlanResultIdValid(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);

    List<ErrorMessageDto> verifyGetTestCaseResultByPlanResultId(Long testPlanResultId);

    List<ErrorMessageDto> isTestPlanResultIdValid(Long testPlanResultId);
}
