package cana.codelessautomation.api.resources.schedule.service.dtos;

import cana.codelessautomation.api.commons.dtos.BaseDto;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;
import lombok.Data;

@Data
public class ReScheduleStatusDto extends BaseDto {
    private Long scheduleId;
    private ScheduleDao scheduleDao;
    private String comments;
    private ScheduleIterationDao scheduleIteration;
}
