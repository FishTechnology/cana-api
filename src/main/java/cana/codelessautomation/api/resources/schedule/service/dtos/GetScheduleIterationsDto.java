package cana.codelessautomation.api.resources.schedule.service.dtos;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;
import lombok.Data;

import java.util.List;

@Data
public class GetScheduleIterationsDto {
    private Long applicationId;
    private Long scheduleId;
    private ScheduleDao schedule;
    private ApplicationDao application;
    private List<ScheduleIterationDao> scheduleIterations;
}
