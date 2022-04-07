package cana.codelessautomation.api.resources.schedule.service.dtos;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import lombok.Data;

@Data
public class UpdateScheduleSessionDto {
    private Long scheduleId;
    private Long iterationId;
    private String sessionId;
    private ScheduleDao schedule;
    private ApplicationDao application;
}
