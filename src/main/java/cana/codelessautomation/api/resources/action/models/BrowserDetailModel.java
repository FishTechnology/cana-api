package cana.codelessautomation.api.resources.action.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.services.action.repositories.daos.BrowserActionTypeDao;
import cana.codelessautomation.api.services.action.repositories.daos.ConditionType;
import cana.codelessautomation.api.services.schedule.errorcodes.ScheduleServiceErrorCode;
import lombok.Data;

@Data
public class BrowserDetailModel {
    @ValidEnumString(enumRef = BrowserActionTypeDao.class, isOptional = true, message = ScheduleServiceErrorCode.getBrowserActionTypeInValidError)
    private String actionType;
    private String comments;
    private String value;
    @ValidEnumString(enumRef = ConditionType.class, isOptional = true, message = ScheduleServiceErrorCode.getBrowserConditionTypeInValidError)
    private String conditionType;
}
