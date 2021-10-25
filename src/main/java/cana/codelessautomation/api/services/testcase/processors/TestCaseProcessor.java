package cana.codelessautomation.api.services.testcase.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseDto;
import cana.codelessautomation.api.services.testcase.dtos.GetTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;

import java.util.List;

public interface TestCaseProcessor {
    List<ErrorMessageDto> processCreateTestCase(CreateTestCaseDto createTestCase);

    List<ErrorMessageDto> createTestCase(CreateTestCaseDto createTestCase);

    List<ErrorMessageDto> getTestPlanAndTestCaseGrouping(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto);

    List<ErrorMessageDto> processCreateTestCaseByPlanId(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId);

    List<ErrorMessageDto> createTestPlanTestCaseMapping(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId);

    Long getTestCaseOrderNumber(Long testPlanId);

    List<ErrorMessageDto> createTestCase(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId);

    List<TestCaseDao> processGetTestCaseByUserId(Long userId);

    List<ErrorMessageDto> processGetTestCaseByTestPlanId(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto);

    List<TestCaseDao> getTestCaseByUserId(Long userId);
}
