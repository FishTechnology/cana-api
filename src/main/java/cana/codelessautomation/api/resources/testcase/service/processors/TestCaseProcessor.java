package cana.codelessautomation.api.resources.testcase.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.testcase.service.dtos.*;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.resources.testplan.service.dtos.CopyTestPlanDto;

import java.util.List;

public interface TestCaseProcessor {
    List<ErrorMessageDto> copyTestCase(CopyTestPlanDto copyTestPlanDto);

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

    List<ErrorMessageDto> processUpdateTestCaseOrder(UpdateTestCaseOrderDto updateTestCaseOrderDto);

    List<ErrorMessageDto> updateTestCaseOrder(UpdateTestCaseOrderDto updateTestCaseOrderDto);

    List<ErrorMessageDto> getTestCaseByTestCaseIds(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto);
}
