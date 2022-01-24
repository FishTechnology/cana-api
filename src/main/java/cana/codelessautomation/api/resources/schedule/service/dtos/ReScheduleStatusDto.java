package cana.codelessautomation.api.resources.schedule.service.dtos;

import cana.codelessautomation.api.commons.dtos.BaseDto;
import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;
import lombok.Data;

@Data
public class ReScheduleStatusDto extends BaseDto {
    private Long scheduleId;
    private ScheduleDao scheduleDao;
    private String comments;
    private Long applicationId;
    private Long userId;
    private ApplicationDao application;
    private ScheduleIterationDao scheduleIteration;
}
