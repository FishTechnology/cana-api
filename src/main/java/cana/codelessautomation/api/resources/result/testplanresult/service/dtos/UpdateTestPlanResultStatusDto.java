package cana.codelessautomation.api.resources.result.testplanresult.service.dtos;

import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultStatusDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UpdateTestPlanResultStatusDto {
    private Long testPlanResultId;
    private Long scheduleIterationId;
    private TestPlanResultStatusDao status;
    private String errorMessage;
    private String totalDuration;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private ScheduleIterationDao scheduleIteration;
    private TestPlanResultDao testPlanResult;
    private OffsetDateTime startedOn;
    private OffsetDateTime completedOn;
}
