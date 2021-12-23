package cana.codelessautomation.api.resources.schedule.service.dtos;

import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;
import lombok.Data;

@Data
public class ScheduleIterationResultDto {
    private Long scheduleId;
    private Long scheduleIterationId;
    private ScheduleDao schedule;
    private ScheduleIterationDao scheduleIteration;
    private TestPlanResultDao testPlanResultDao;
}
