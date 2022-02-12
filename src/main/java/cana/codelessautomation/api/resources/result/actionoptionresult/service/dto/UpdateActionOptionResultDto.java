package cana.codelessautomation.api.resources.result.actionoptionresult.service.dto;

import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultStatus;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;
import lombok.Data;

@Data
public class UpdateActionOptionResultDto {
    private ActionOptionResultStatus status;
    private ActionResultDao actionResultDao;
    private ActionOptionResultDao actionOptionResultDao;
    private Long actionOptionResultId;
    private Long actionResultId;
    private String errorMessage;
    private Long totalDuration;
}
