package cana.codelessautomation.api.services.schedule.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.schedule.dtos.CopyTestPlanDetailDto;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleIterationResultDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleSummaryDto;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;

import java.util.List;

public interface ScheduleServiceVerifier {
    List<ErrorMessageDto> verifyCreateSchedule(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> isTestPlanIdValid(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> isEnvironmentIdValid(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> isScheduleIterationIdValid(ScheduleIterationResultDto scheduleIterationResultDto);

    List<ErrorMessageDto> isScheduleIdValid(ScheduleIterationResultDto scheduleIterationResultDto);

    KeyValue<List<ErrorMessageDto>, ScheduleIterationDao> isScheduleIterationIdValid(Long scheduleIterationId);

    List<ErrorMessageDto> isScheduleIdValid(CopyTestPlanDetailDto copyTestPlanDetailDto);

    KeyValue<List<ErrorMessageDto>, ScheduleDao> isScheduleIdValid(Long scheduleId);

    List<ErrorMessageDto> isUserId(ScheduleSummaryDto scheduleSummaryDto);

    List<ErrorMessageDto> isUserId(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> verifyGetScheduleSummary(ScheduleSummaryDto scheduleSummaryDto);

    List<ErrorMessageDto> verifyCopyTestPlanDetail(CopyTestPlanDetailDto copyTestPlanDetailDto);

    List<ErrorMessageDto> verifyGetScheduleIterationResult(ScheduleIterationResultDto scheduleIterationResultDto);
}
