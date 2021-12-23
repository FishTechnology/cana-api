package cana.codelessautomation.api.resources.schedule.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.resources.commonmodels.BrowserType;
import cana.codelessautomation.api.resources.schedule.service.errorcodes.ScheduleServiceErrorCode;
import lombok.Data;

@Data
public class CreateScheduleModel {
    private long environmentId;
    private Long userId;
    private Boolean isRecordVideoEnabled;
    private Boolean isDisableScreenshot;
    private Boolean isCaptureNetworkTraffic;
    @ValidEnumString(enumRef = BrowserType.class, message = ScheduleServiceErrorCode.getBrowserTypeInValidError)
    private String browserType;
    private CreateNotificationModel notification;
}
