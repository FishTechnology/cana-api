package cana.codelessautomation.api.resources.result.testcaseresult.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.result.testcaseresult.service.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;

import java.util.List;

public interface TestCaseResultVerifier {
    List<ErrorMessageDto> verifyUpdateTestCaseResultStatus(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);

    List<ErrorMessageDto> isTestCaseResultIdValid(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);

    KeyValue<List<ErrorMessageDto>, TestCaseResultDao> isTestCaseResultIdValid(Long testCaseResultId);

    List<ErrorMessageDto> isTestPlanResultIdValid(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);

    List<ErrorMessageDto> verifyGetTestCaseResultByPlanResultId(Long testPlanResultId);

    List<ErrorMessageDto> isTestPlanResultIdValid(Long testPlanResultId);
}
