package cana.codelessautomation.api.resources.result.actionresult.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.services.results.action.errorcodes.ActionResultErrorCode;
import cana.codelessautomation.api.services.results.action.repositories.daos.enums.ActionResultStatusDao;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UpdateActionResultModel {
    private Long actionId;
    private OffsetDateTime startedOn;
    private OffsetDateTime completedOn;
    private String errorMessage;
    @ValidEnumString(enumRef = ActionResultStatusDao.class, message = ActionResultErrorCode.getActionResultStatusInValid)
    private String status;
}
