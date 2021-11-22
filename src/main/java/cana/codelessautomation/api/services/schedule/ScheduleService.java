package cana.codelessautomation.api.services.schedule;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.schedule.dtos.*;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.entities.ScheduleDetailEntity;

import java.util.List;

public interface ScheduleService {
    List<ErrorMessageDto> createSchedule(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> getScheduleSummary(ScheduleSummaryDto scheduleSummaryDto);

    List<ScheduleIterationDao> getScheduleIterations(Long scheduleId);

    List<ErrorMessageDto> copyTestPlanDetail(CopyTestPlanDetailDto copyTestPlanDetailDto);

    List<ErrorMessageDto> getScheduleIterationResult(ScheduleIterationResultDto scheduleIterationResultDto);

    ScheduleDetailEntity getScheduler(Long scheduleId);

    List<ErrorMessageDto> setAsInProgress(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

    List<ErrorMessageDto> updateScheduleStatus(UpdateScheduleStatusReadyDto updateScheduleStatusDto);

    List<ErrorMessageDto> reSchedule(ReScheduleStatusDto reScheduleStatusDto);
}
