package cana.codelessautomation.api.services.schedule.dtos;

import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleStatusDao;
import lombok.Data;

@Data
public class UpdateScheduleStatusReadyDto {
    private Long scheduleId;
    private ScheduleStatusDao scheduleStatus;
    private ScheduleDao schedule;
    private String errorMessage;
    private String totalDuration;
}
