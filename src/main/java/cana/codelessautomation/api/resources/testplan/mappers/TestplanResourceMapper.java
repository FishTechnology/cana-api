package cana.codelessautomation.api.resources.testplan.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduleTestPlanModel;
import cana.codelessautomation.api.resources.testplan.models.CreateTestplanModel;
import cana.codelessautomation.api.resources.testplan.models.TestPlanModel;
import cana.codelessautomation.api.resources.testplan.models.UpdateTestplanModel;
import cana.codelessautomation.api.resources.testplan.models.UpdateTestplanStatusModel;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.CreateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.DeleteTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.UpdateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.UpdateTestplanStatusDto;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.entities.TestPlanSummaryDaoEntity;

import java.util.List;

public interface TestplanResourceMapper {
    CreateTestplanDto mapCreateTestplanDto(CreateTestplanModel createTestplanModel);

    ResultModel mapResultModel(CreateTestplanDto createTestplanDto, List<ErrorMessageDto>  errorMessages);

    List<TestPlanModel> mapTestPlanModels(List<TestplanDao> testPlanDaos);

    TestPlanModel mapTestPlanModel(TestplanDao testPlanDao);

    DeleteTestplanDto mapDeleteTestplanDto(Long testplanId);

    UpdateTestplanDto mapUpdateTestplanDto(UpdateTestplanModel updateTestplanModel, Long testplanId);

    UpdateTestplanStatusDto mapUpdateTestplanStatusDto(UpdateTestplanStatusModel updateTestplanStatusModel, Long testplanId);

    ScheduleTestPlanModel mapTestPlanModel(TestPlanSummaryDaoEntity testplanDaos);
}
