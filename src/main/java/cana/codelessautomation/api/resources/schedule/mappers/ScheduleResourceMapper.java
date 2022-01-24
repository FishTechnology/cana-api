package cana.codelessautomation.api.resources.schedule.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.result.actionresult.models.ActionResultSummaryModel;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.resources.result.testcaseresult.models.TestCaseResultSummaryModel;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.resources.result.testplanresult.models.TestPlanResultSummaryModel;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.resources.schedule.models.*;
import cana.codelessautomation.api.resources.schedule.service.dtos.*;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.entities.ScheduleEntity;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.entities.ScheduleSummaryEntity;

import java.util.List;

public interface ScheduleResourceMapper {
    ResultModel mapResultModel(CreateScheduleDto createScheduleDto, List<ErrorMessageDto> errorMessages);

    CreateScheduleDto mapCreateScheduleDto(Long applicationId, CreateScheduleModel createScheduleModel, Long testPlanId);

    ScheduleSummaryDto mapScheduleSummaryDto(Long applicationId, int pageSize, int pageNumber);

    SchedulePageModel mapSchedulePageModel(ScheduleSummaryDto scheduleSummaryDto, List<ErrorMessageDto> errorMessages);

    List<ScheduleIterationModel> mapScheduleIterationModels(List<ScheduleIterationDao> scheduleIterationDaos);

    ScheduleIterationModel mapScheduleIterationModel(ScheduleIterationDao scheduleIterationDao);

    CopyTestPlanDetailDto mapCopyTestPlanDetailDto(Long scheduleId);

    ScheduleIterationResultDto mapScheduleIterationResultDto(Long scheduleId, Long scheduleIterationId);

    ScheduleIterationResultModel mapScheduleIterationResultModel(List<ErrorMessageDto> errors, ScheduleIterationResultDto scheduleIterationResultDto);

    TestPlanResultSummaryModel mapTestPlanResultSummaryModel(TestPlanResultDao testPlanResultDao);

    TestCaseResultSummaryModel mapTestCaseResultModel(TestCaseResultDao testCaseResultDao);

    ActionResultSummaryModel mapActionResultModel(ActionResultDao actionResultDao);

    ScheduleModel mapScheduleModel(ScheduleDao scheduleDao);

    ScheduleModel mapScheduleModel(ScheduleSummaryEntity scheduleDetailEntity);

    ScheduleDetailModel mapScheduleDetailModel(ScheduleSummaryEntity scheduleDetailEntity);

    UpdateScheduleStatusReadyDto mapUpdateScheduleStatusDto(Long scheduleId, UpdateScheduleStatusModel scheduleStatus);

    ReScheduleStatusDto mapReScheduleStatusDto(Long applicationId, Long scheduleId, ReScheduleModel reScheduleModel);

    ScheduleModel mapScheduleIterationResultModel(ScheduleEntity scheduleEntity);

    List<ScheduleModel> mapScheduleModels(List<ScheduleEntity> scheduleEntities);

    UpdateScheduleStatusReadyDto mapUpdateScheduleStatusDto(Long applicationId, Long scheduleId, UpdateScheduleStatusModel updateScheduleStatusModel);
}
