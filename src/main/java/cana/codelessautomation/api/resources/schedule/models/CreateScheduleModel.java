package cana.codelessautomation.api.resources.schedule.models;

import lombok.Data;

@Data
public class CreateScheduleModel {
    private long environmentId;
    private Long userId;
    private Boolean isRecordVideoEnabled;
    private Boolean isDisableScreenshot;
    private Boolean isCaptureNetworkTraffic;
}
