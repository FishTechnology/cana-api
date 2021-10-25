package cana.codelessautomation.api.resources.testcase.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.testcase.models.CreateTestCaseByTestPlanIdModel;
import cana.codelessautomation.api.resources.testcase.models.CreateTestCaseModel;
import cana.codelessautomation.api.resources.testcase.models.TestCaseModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseDto;
import cana.codelessautomation.api.services.testcase.dtos.GetTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;

import java.util.List;

public interface TestCaseServiceMapper {
    CreateTestCaseDto mapCreateTestCaseDto(CreateTestCaseModel createTestCaseModel);

    ResultModel mapResultModel(CreateTestCaseDto createTestCaseDto, List<ErrorMessageDto> errorMessages);

    ResultModel mapResultModel(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId, List<ErrorMessageDto> errorMessages);

    CreateTestCaseByTestPlanIdDto mapCreateTestCaseByTestPlanIdDto(CreateTestCaseByTestPlanIdModel createTestCaseByTestPlanIdModel, Long testPlanId);

    List<TestCaseModel> mapTestCaseModels(List<TestCaseDao> testCaseDaos);

    GetTestCaseByTestPlanIdDto mapGetTestCaseByTestPlanIdDto(Long testPlanId);

    List<TestCaseModel> mapTestCaseModels(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto);
}
