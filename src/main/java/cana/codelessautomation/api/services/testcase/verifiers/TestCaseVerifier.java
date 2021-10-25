package cana.codelessautomation.api.services.testcase.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseDto;
import cana.codelessautomation.api.services.testcase.dtos.GetTestCaseByTestPlanIdDto;

import java.util.List;

public interface TestCaseVerifier {
    List<ErrorMessageDto> verifyCreateTestCase(CreateTestCaseDto createTestCase);

    List<ErrorMessageDto> isTestPlanIdValidId(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId);

    List<ErrorMessageDto> isTestCaseNameByTestPlanIdValid(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId);

    List<ErrorMessageDto> checkTestCaseNameValid(List<Long> testCaseIds, String testCaseName);

    List<ErrorMessageDto> isUserIdValid(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId);

    List<ErrorMessageDto> isTestCaseNameValid(CreateTestCaseDto createTestCase);

    List<ErrorMessageDto> isTestCaseNameValid(Long userId,String testCaseName);

    List<ErrorMessageDto> isUserIdValid(CreateTestCaseDto createTestCase);

    List<ErrorMessageDto> verifyCreateTestCaseByPlanId(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId);

    List<ErrorMessageDto> verifyGetTestCaseByUserId(Long userId);

    List<ErrorMessageDto> isTestPlanIdValid(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto);

    List<ErrorMessageDto> isUserIdValid(Long userId);

    List<ErrorMessageDto> verifyGetTestCaseByTestPlanId(GetTestCaseByTestPlanIdDto testPlanId);
}
