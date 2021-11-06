package cana.codelessautomation.api.services.schedule.dtos;

import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.services.testplan.repositories.daos.entities.TestPlanSummaryDaoEntity;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CopyTestPlanDetailDto {
    private Long scheduleId;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private ScheduleDao schedule;
    private Long testPlanResultId;
    private ScheduleIterationDao scheduleIteration;
    private TestPlanSummaryDaoEntity testPlanSummary;
}
