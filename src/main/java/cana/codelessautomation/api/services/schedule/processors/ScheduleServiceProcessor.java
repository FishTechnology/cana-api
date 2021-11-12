package cana.codelessautomation.api.services.schedule.processors;

import cana.codelessautomation.api.services.action.repositories.daos.entities.ActionDaoEntity;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.services.schedule.dtos.CopyTestPlanDetailDto;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleIterationResultDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleSummaryDto;
import cana.codelessautomation.api.services.schedule.repositories.daos.entities.ScheduleDetailEntity;

import java.util.List;

public interface ScheduleServiceProcessor {
    List<ErrorMessageDto> processCreateSchedule(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> getTestPlanResultDetails(ScheduleIterationResultDto scheduleIterationResultDto);

    List<ErrorMessageDto> createDraftTestPlanResult(CopyTestPlanDetailDto copyTestPlanDetailDto);

    List<ErrorMessageDto> createActionResult(CopyTestPlanDetailDto copyTestPlanDetailDto, TestCaseResultDao testCaseResultDao, List<ActionDaoEntity> actionDaoEntities);

    List<ErrorMessageDto> createTestCaseResult(CopyTestPlanDetailDto copyTestPlanDetailDto);

    List<ErrorMessageDto> createTestPlanResult(CopyTestPlanDetailDto copyTestPlanDetailDto);

    List<ErrorMessageDto> getTestPlanDetails(CopyTestPlanDetailDto copyTestPlanDetailDto);

    List<ErrorMessageDto> getScheduleIteration(CopyTestPlanDetailDto copyTestPlanDetailDto);

    List<ErrorMessageDto> getScheduleSummary(ScheduleSummaryDto scheduleSummaryDto);

    List<ErrorMessageDto> createScheduleIteration(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> createSchedule(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> createNotification(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> processGetScheduleSummary(ScheduleSummaryDto scheduleSummaryDto);

    List<ErrorMessageDto> processCopyTestPlanDetail(CopyTestPlanDetailDto copyTestPlanDetailDto);

    List<ErrorMessageDto> processGetScheduleIterationResult(ScheduleIterationResultDto scheduleIterationResultDto);

    ScheduleDetailEntity processGetScheduleDetail(Long scheduleId);
}
