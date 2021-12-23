package cana.codelessautomation.api.resources.testcase.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.testcase.service.dtos.*;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;

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

    List<ErrorMessageDto> isTestCaseNameValid(UpdateTestCaseByIdDto updateTestCaseByIdDto);

    List<ErrorMessageDto> isTestCaseIdValid(UpdateTestCaseByIdDto updateTestCaseByIdDto);

    List<ErrorMessageDto> isTestCaseIdValid(GetTestCaseByIdDto getTestCaseByIdDto);

    List<ErrorMessageDto> isTestCaseIdMapToAnyTestPlanIdValid(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto);

    List<ErrorMessageDto> isTestPlanAndTestCaseMappingValid(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto);

    List<ErrorMessageDto> isTestCaseIdValid(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto);

    KeyValue<List<ErrorMessageDto>, TestCaseDao> isTestCaseIdValid(Long testCaseId);

    List<ErrorMessageDto> isTestPlanIdValid(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto);

    List<ErrorMessageDto> isTestPlanIdValid(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto);

    List<ErrorMessageDto> isUserIdValid(Long userId);

    List<ErrorMessageDto> verifyGetTestCaseByTestPlanId(GetTestCaseByTestPlanIdDto testPlanId);

    List<ErrorMessageDto> verifyCheckTestCaseIsDeletable(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto);

    List<ErrorMessageDto> verifyGetTestCaseById(GetTestCaseByIdDto getTestCaseByIdDto);

    List<ErrorMessageDto> verifyUpdateTestCaseById(UpdateTestCaseByIdDto updateTestCaseByIdDto);
}
