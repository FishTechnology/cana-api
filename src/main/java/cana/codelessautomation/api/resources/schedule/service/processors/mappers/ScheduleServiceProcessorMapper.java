package cana.codelessautomation.api.resources.schedule.service.processors.mappers;

import cana.codelessautomation.api.resources.schedule.service.dtos.CreateScheduleDto;
import cana.codelessautomation.api.resources.schedule.service.dtos.ReScheduleStatusDto;
import cana.codelessautomation.api.resources.schedule.service.dtos.UpdateScheduleStatusReadyDto;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;

public interface ScheduleServiceProcessorMapper {
    ScheduleDao mapScheduleDao(CreateScheduleDto createScheduleDto);

    ScheduleIterationDao mapScheduleIterationDao(CreateScheduleDto createScheduleDto);

    ScheduleDao mapScheduleDao(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto);

    ScheduleIterationDao mapScheduleIterationDao(UpdateScheduleStatusReadyDto updateScheduleStatusDto);

    ScheduleDao mapScheduleDao(ReScheduleStatusDto reScheduleStatusDto);

    ScheduleIterationDao mapScheduleIterationDao(ReScheduleStatusDto reScheduleStatusDto);

    ScheduleIterationDao mapScheduleIterationDao(ReScheduleStatusDto reScheduleStatusDto, ScheduleIterationDao scheduleIterationDao);
}
