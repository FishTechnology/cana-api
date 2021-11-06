package cana.codelessautomation.api.services.schedule.dtos;

import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;
import lombok.Data;

@Data
public class ScheduleIterationResultDto {
    private Long scheduleId;
    private Long scheduleIterationId;
    private ScheduleDao schedule;
    private ScheduleIterationDao scheduleIteration;
    private TestPlanResultDao testPlanResultDao;
}
