package cana.codelessautomation.api.resources.result.testcaseresult.service.dtos;

import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultStatusDao;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UpdateTestCaseResultStatusDto {
    private Long testPlanResultId;
    private Long testCaseResultId;
    private TestCaseResultStatusDao status;
    private String errorMessage;
    private String totalDuration;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private OffsetDateTime startedOn;
    private OffsetDateTime completedOn;
    private TestPlanResultDao testPlanResult;
    private TestCaseResultDao testCaseResultDao;
}
