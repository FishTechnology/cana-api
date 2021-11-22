package cana.codelessautomation.api.services.schedule.dtos;

import cana.codelessautomation.api.services.common.dtos.BaseDto;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;
import lombok.Data;

@Data
public class ReScheduleStatusDto extends BaseDto {
    private Long scheduleId;
    private ScheduleDao scheduleDao;
    private String comments;
    private ScheduleIterationDao scheduleIteration;
}
