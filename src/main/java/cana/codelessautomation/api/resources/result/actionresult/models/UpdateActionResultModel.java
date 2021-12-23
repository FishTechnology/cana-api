package cana.codelessautomation.api.resources.result.actionresult.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.resources.result.actionresult.service.errorcodes.ActionResultErrorCode;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.enums.ActionResultStatusDao;
import lombok.Data;

@Data
public class UpdateActionResultModel {
    @ValidEnumString(enumRef = ActionResultStatusDao.class, message = ActionResultErrorCode.getActionResultStatusInValid)
    private String status;
    private String errorMessage;
    private String totalDuration;
    private String modifiedBy;
    private String startedOn;
    private String completedOn;
}
