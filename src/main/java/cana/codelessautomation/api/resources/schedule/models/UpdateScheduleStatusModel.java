package cana.codelessautomation.api.resources.schedule.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.services.schedule.errorcodes.ScheduleServiceErrorCode;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleStatusDao;
import lombok.Data;

@Data
public class UpdateScheduleStatusModel {
    @ValidEnumString(enumRef = ScheduleStatusDao.class, message = ScheduleServiceErrorCode.getScheduleStatusInValid)
    private String status;
    private String errorMessage;
    private String totalDuration;
}
