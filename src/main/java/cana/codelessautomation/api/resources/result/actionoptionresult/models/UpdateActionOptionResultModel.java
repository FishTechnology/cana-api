package cana.codelessautomation.api.resources.result.actionoptionresult.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.errorcodes.ActionOptionResultErrorCodes;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultStatus;
import lombok.Data;

@Data
public class UpdateActionOptionResultModel {
    @ValidEnumString(enumRef = ActionOptionResultStatus.class, message = ActionOptionResultErrorCodes.getActionOptionResultStatusInValid)
    private String status;
    private String errorMessage;
    private Long totalDuration;
}
