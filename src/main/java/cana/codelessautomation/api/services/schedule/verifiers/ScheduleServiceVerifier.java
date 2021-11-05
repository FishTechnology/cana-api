package cana.codelessautomation.api.services.schedule.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleSummaryDto;

import java.util.List;

public interface ScheduleServiceVerifier {
    List<ErrorMessageDto> verifyCreateSchedule(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> isTestPlanIdValid(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> isEnvironmentIdValid(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> isUserId(ScheduleSummaryDto scheduleSummaryDto);

    List<ErrorMessageDto> isUserId(CreateScheduleDto createScheduleDto);

    List<ErrorMessageDto> verifyGetScheduleSummary(ScheduleSummaryDto scheduleSummaryDto);
}
