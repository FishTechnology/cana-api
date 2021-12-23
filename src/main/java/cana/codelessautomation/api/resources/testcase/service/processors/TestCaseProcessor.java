package cana.codelessautomation.api.resources.testcase.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.testcase.service.dtos.CreateTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.resources.testcase.service.dtos.CreateTestCaseDto;
import cana.codelessautomation.api.resources.testcase.service.dtos.GetTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.resources.testcase.service.dtos.UpdateTestCaseByIdDto;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;

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

    List<ErrorMessageDto> processUpdateTestCaseById(UpdateTestCaseByIdDto updateTestCaseByIdDto);

    List<ErrorMessageDto> updateTestCase(UpdateTestCaseByIdDto updateTestCaseByIdDto);
}
