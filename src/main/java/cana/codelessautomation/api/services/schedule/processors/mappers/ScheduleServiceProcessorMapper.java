package cana.codelessautomation.api.services.schedule.processors.mappers;

import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.ReScheduleStatusDto;
import cana.codelessautomation.api.services.schedule.dtos.UpdateScheduleStatusReadyDto;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;

public interface ScheduleServiceProcessorMapper {
    ScheduleDao mapScheduleDao(CreateScheduleDto createScheduleDto);

    ScheduleIterationDao mapScheduleIterationDao(CreateScheduleDto createScheduleDto);

    ScheduleDao mapScheduleDao(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

    ScheduleIterationDao mapScheduleIterationDao(UpdateScheduleStatusReadyDto updateScheduleStatusDto);

    ScheduleDao mapScheduleDao(ReScheduleStatusDto reScheduleStatusDto);

    ScheduleIterationDao mapScheduleIterationDao(ReScheduleStatusDto reScheduleStatusDto);

    ScheduleIterationDao mapScheduleIterationDao(ReScheduleStatusDto reScheduleStatusDto, ScheduleIterationDao scheduleIterationDao);
}
