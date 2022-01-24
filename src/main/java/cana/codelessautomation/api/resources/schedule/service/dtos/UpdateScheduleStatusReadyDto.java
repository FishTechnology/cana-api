package cana.codelessautomation.api.resources.schedule.service.dtos;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleStatusDao;
import lombok.Data;

@Data
public class UpdateScheduleStatusReadyDto {
    private Long applicationId;
    private Long scheduleId;
    private ScheduleStatusDao scheduleStatus;
    private ScheduleDao schedule;
    private String errorMessage;
    private String totalDuration;
    private ApplicationDao application;
}
