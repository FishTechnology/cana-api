package cana.codelessautomation.api.services.schedule;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleSummaryDto;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;

import java.util.List;

public interface ScheduleService {
    List<ErrorMessageDto> createSchedule(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> getScheduleSummary(ScheduleSummaryDto scheduleSummaryDto);

    List<ScheduleIterationDao> getScheduleIterations(Long scheduleId);
}
