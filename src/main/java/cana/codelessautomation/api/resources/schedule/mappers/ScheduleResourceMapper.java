package cana.codelessautomation.api.resources.schedule.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.result.actionresult.models.ActionResultModel;
import cana.codelessautomation.api.resources.result.testcaseresult.models.TestCaseResultModel;
import cana.codelessautomation.api.resources.result.testplanresult.models.TestPlanResultSummaryModel;
import cana.codelessautomation.api.resources.schedule.models.*;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.services.schedule.dtos.CopyTestPlanDetailDto;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleIterationResultDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleSummaryDto;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;

import java.util.List;

public interface ScheduleResourceMapper {
    ResultModel mapResultModel(CreateScheduleDto createScheduleDto, List<ErrorMessageDto> errorMessages);

    CreateScheduleDto mapCreateScheduleDto(CreateScheduleModel createScheduleModel, Long testPlanId);

    ScheduleSummaryDto mapScheduleSummaryDto(Long userId, int pageSize, int pageNumber);

    SchedulePageModel mapSchedulePageModel(ScheduleSummaryDto scheduleSummaryDto, List<ErrorMessageDto> errorMessages);

    List<ScheduleIterationModel> mapScheduleIterationModels(List<ScheduleIterationDao> scheduleIterationDaos);

    ScheduleIterationModel mapScheduleIterationModel(ScheduleIterationDao scheduleIterationDao);

    CopyTestPlanDetailDto mapCopyTestPlanDetailDto(Long scheduleId);

    ScheduleIterationResultDto mapScheduleIterationResultDto(Long scheduleId, Long scheduleIterationId);

    ScheduleIterationResultModel mapScheduleIterationResultModel(List<ErrorMessageDto> errors, ScheduleIterationResultDto scheduleIterationResultDto);

    TestPlanResultSummaryModel mapTestPlanResultSummaryModel(TestPlanResultDao testPlanResultDao);

    TestCaseResultModel mapTestCaseResultModel(TestCaseResultDao testCaseResultDao);

    ActionResultModel mapActionResultModel(ActionResultDao actionResultDao);

    ScheduleModel mapScheduleModel(ScheduleDao scheduleDao);
}
