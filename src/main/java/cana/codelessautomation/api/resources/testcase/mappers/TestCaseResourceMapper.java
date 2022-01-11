package cana.codelessautomation.api.resources.testcase.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduledTestCaseModel;
import cana.codelessautomation.api.resources.testcase.models.*;
import cana.codelessautomation.api.resources.testcase.service.dtos.*;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.entities.TestCaseDaoEntity;

import java.util.List;

public interface TestCaseResourceMapper {
    CreateTestCaseDto mapCreateTestCaseDto(CreateTestCaseModel createTestCaseModel);

    ResultModel mapResultModel(CreateTestCaseDto createTestCaseDto, List<ErrorMessageDto> errorMessages);

    ResultModel mapResultModel(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId, List<ErrorMessageDto> errorMessages);

    CreateTestCaseByTestPlanIdDto mapCreateTestCaseByTestPlanIdDto(CreateTestCaseByTestPlanIdModel createTestCaseByTestPlanIdModel, Long testPlanId);

    List<TestCaseModel> mapTestCaseModels(List<TestCaseDao> testCaseDaos);

    GetTestCaseByTestPlanIdDto mapGetTestCaseByTestPlanIdDto(Long testPlanId);

    List<TestCaseModel> mapTestCaseModels(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto);

    CheckTestCaseIsDeletableDto mapCheckTestCaseIsDeletableDto(Long testCaseId);

    CheckTestCaseIsDeletableModel mapCheckTestCaseIsDeletableModel(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto, List<ErrorMessageDto> errors);

    GetTestCaseByIdDto mapGetTestCaseByIdDto(Long testCaseId);

    TestCaseModel mapTestCaseModel(TestCaseDao testCaseDao);

    TestCaseModel mapTestCaseModel(GetTestCaseByIdDto getTestCaseByIdDto);

    UpdateTestCaseByTestPlanIdDto mapUpdateTestCaseByTestPlanIdDto(Long testPlanId, Long testCaseId, UpdateTestCaseModel updateTestCaseModel);

    UpdateTestCaseByIdDto mapUpdateTestCaseByIdDto(Long testCaseId, UpdateTestCaseModel updateTestCaseModel);

    ScheduledTestCaseModel mapScheduledTestCaseModel(TestCaseDaoEntity testCase);

    UpdateTestCaseOrderDto mapUpdateTestCaseOrderDto(Long testPlanId, UpdateTestCaseOrderModel updateTestCaseOrderModel);
}
