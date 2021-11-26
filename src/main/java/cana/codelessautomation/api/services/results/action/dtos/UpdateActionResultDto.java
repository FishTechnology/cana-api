package cana.codelessautomation.api.services.results.action.dtos;

import cana.codelessautomation.api.services.action.repositories.daos.ActionDao;
import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.services.results.action.repositories.daos.enums.ActionResultStatusDao;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UpdateActionResultDto {
    private Long testCaseResultId;
    private Long actionResultId;
    private Long actionId;
    private OffsetDateTime startedOn;
    private OffsetDateTime completedOn;
    private String errorMessage;
    private ActionResultStatusDao status;
    private ActionDao actionDao;
    private TestCaseResultDao testCaseResult;
    private ActionResultDao actionResult;
    private String totalDuration;
}
