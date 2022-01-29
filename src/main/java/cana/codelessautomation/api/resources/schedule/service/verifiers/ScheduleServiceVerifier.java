package cana.codelessautomation.api.resources.schedule.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.schedule.service.dtos.*;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;

import java.util.List;

public interface ScheduleServiceVerifier {
    List<ErrorMessageDto> verifyCreateSchedule(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> isTestPlanIdValid(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> isEnvironmentIdValid(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> isScheduleIdValid(GetScheduleIterationsDto getScheduleIterationsDto);

    List<ErrorMessageDto> isApplicationIdValid(GetScheduleIterationsDto getScheduleIterationsDto);

    List<ErrorMessageDto> isApplicationIdValid(ReScheduleStatusDto reScheduleStatusDto);

    List<ErrorMessageDto> isScheduleStatusValid(ReScheduleStatusDto reScheduleStatusDto);

    List<ErrorMessageDto> isScheduleIdValid(ReScheduleStatusDto reScheduleStatusDto);

    List<ErrorMessageDto> isTestPlanStatusValid(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

    List<ErrorMessageDto> isScheduleIterationStatusValid(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

    List<ErrorMessageDto> isScheduleStatusValid(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

    List<ErrorMessageDto> isScheduleIdValid(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

    List<ErrorMessageDto> isScheduleIterationIdValid(ScheduleIterationResultDto scheduleIterationResultDto);

    List<ErrorMessageDto> isScheduleIdValid(ScheduleIterationResultDto scheduleIterationResultDto);

    KeyValue<List<ErrorMessageDto>, ScheduleIterationDao> isScheduleIterationIdValid(Long scheduleIterationId);

    List<ErrorMessageDto> isScheduleIdValid(CopyTestPlanDetailDto copyTestPlanDetailDto);

    KeyValue<List<ErrorMessageDto>, ScheduleDao> isScheduleIdValid(Long scheduleId);

    List<ErrorMessageDto> isUserId(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> isCheckTestPlanAlreadyScheduled(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> isTestPlanStatusValid(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> verifyGetScheduleSummary(ScheduleSummaryDto scheduleSummaryDto);

    List<ErrorMessageDto> isApplicationIdValid(ScheduleSummaryDto scheduleSummaryDto);

    List<ErrorMessageDto> verifyCopyTestPlanDetail(CopyTestPlanDetailDto copyTestPlanDetailDto);

    List<ErrorMessageDto> verifyGetScheduleIterationResult(ScheduleIterationResultDto scheduleIterationResultDto);

    List<ErrorMessageDto> verifyUpdateScheduleStatus(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

    List<ErrorMessageDto> isApplicationIdValid(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

    List<ErrorMessageDto> verifyReSchedule(ReScheduleStatusDto reScheduleStatusDto);

    List<ErrorMessageDto> verifyGetScheduleIterations(GetScheduleIterationsDto getScheduleIterationsDto);
}
