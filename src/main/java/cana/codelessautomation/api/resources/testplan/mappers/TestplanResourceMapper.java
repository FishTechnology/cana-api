package cana.codelessautomation.api.resources.testplan.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduleTestPlanModel;
import cana.codelessautomation.api.resources.testplan.models.*;
import cana.codelessautomation.api.resources.testplan.service.dtos.*;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.entities.TestPlanSummaryDaoEntity;

import java.util.List;

public interface TestplanResourceMapper {
    CreateTestplanDto mapCreateTestplanDto(Long applicationId, CreateTestplanModel createTestplanModel);

    ResultModel mapResultModel(CreateTestplanDto createTestplanDto, List<ErrorMessageDto> errorMessages);

    List<TestPlanModel> mapTestPlanModels(List<TestplanDao> testPlanDaos);

    TestPlanModel mapTestPlanModel(TestplanDao testPlanDao);

    DeleteTestplanDto mapDeleteTestplanDto(Long testplanId);

    UpdateTestplanDto mapUpdateTestplanDto(UpdateTestplanModel updateTestplanModel, Long testplanId);

    UpdateTestplanStatusDto mapUpdateTestplanStatusDto(UpdateTestplanStatusModel updateTestplanStatusModel, Long testplanId);

    ScheduleTestPlanModel mapTestPlanModel(TestPlanSummaryDaoEntity testplanDaos);

    CopyTestPlanDto mapCopyTestPlanDto(CopyTestPlanModel copyTestPlanModel, Long testplanId);

    ResultModel mapResultModel(List<ErrorMessageDto> errorMessages, CopyTestPlanDto copyTestPlanDto);
}
