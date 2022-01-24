package cana.codelessautomation.api.resources.schedule.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.action.service.repositories.daos.entities.ActionDaoEntity;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.resources.schedule.service.dtos.*;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.entities.ScheduleSummaryEntity;

import java.util.List;

public interface ScheduleServiceProcessor {
    List<ErrorMessageDto> processCreateSchedule(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> getScheduleIterations(GetScheduleIterationsDto getScheduleIterationsDto);

    List<ErrorMessageDto> updateScheduleIteration(ReScheduleStatusDto reScheduleStatusDto);

    List<ErrorMessageDto> updateScheduleStatus(ReScheduleStatusDto reScheduleStatusDto);

    List<ErrorMessageDto> createScheduleIteration(ReScheduleStatusDto reScheduleStatusDto);

    List<ErrorMessageDto> updateScheduleIteration(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

    List<ErrorMessageDto> updateScheduleStatus(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

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

    ScheduleSummaryEntity processGetScheduleDetail(Long scheduleId);

    List<ErrorMessageDto> processUpdateScheduleStatus(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

    List<ErrorMessageDto> processReSchedule(ReScheduleStatusDto reScheduleStatusDto);

    List<ErrorMessageDto> processGetScheduleIterations(GetScheduleIterationsDto getScheduleIterationsDto);
}
