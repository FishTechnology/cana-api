package cana.codelessautomation.api.resources.result.actionresult.service.dtos;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.enums.ActionResultStatusDao;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
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
