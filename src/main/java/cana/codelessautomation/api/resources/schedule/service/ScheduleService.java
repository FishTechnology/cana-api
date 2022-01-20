package cana.codelessautomation.api.resources.schedule.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.schedule.service.dtos.*;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.entities.ScheduleEntity;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.entities.ScheduleSummaryEntity;

import java.util.List;

public interface ScheduleService {
    List<ErrorMessageDto> createSchedule(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> getScheduleSummary(ScheduleSummaryDto scheduleSummaryDto);

    List<ScheduleIterationDao> getScheduleIterations(Long scheduleId);

    List<ErrorMessageDto> copyTestPlanDetail(CopyTestPlanDetailDto copyTestPlanDetailDto);

    List<ErrorMessageDto> getScheduleIterationResult(ScheduleIterationResultDto scheduleIterationResultDto);

    ScheduleSummaryEntity getScheduler(Long scheduleId);

    List<ErrorMessageDto> setAsInProgress(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

    List<ErrorMessageDto> updateScheduleStatus(UpdateScheduleStatusReadyDto updateScheduleStatusDto);

    List<ErrorMessageDto> reSchedule(ReScheduleStatusDto reScheduleStatusDto);

    ScheduleEntity getRunningScheduleByAppId(Long applicationId);

    ScheduleEntity getRunningSchedule();

    ScheduleEntity getScheduleToExecute();
}
