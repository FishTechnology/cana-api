package cana.codelessautomation.api.services.schedule.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleSummaryDto;

import java.util.List;

public interface ScheduleServiceProcessor {
    List<ErrorMessageDto> processCreateSchedule(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> getScheduleSummary(ScheduleSummaryDto scheduleSummaryDto);

    List<ErrorMessageDto> createScheduleIteration(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> createSchedule(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> processGetScheduleSummary(ScheduleSummaryDto scheduleSummaryDto);
}
