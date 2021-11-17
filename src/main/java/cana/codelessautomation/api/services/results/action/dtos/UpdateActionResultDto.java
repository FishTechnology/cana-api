package cana.codelessautomation.api.services.results.action.dtos;

import cana.codelessautomation.api.services.action.repositories.daos.entities.ActionResultStatusDao;
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
}
