package cana.codelessautomation.api.resources.schedule.models;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

@Data
public class ScheduleIterationModel {
    @JMap
    private String id;
    @JMap
    private String scheduleId;
    @JMap
    private String status;
    @JMap
    private String comments;
    @JMap
    private Boolean isRecordVideoEnabled;
    @JMap
    private Boolean isDisableScreenshot;
    @JMap
    private Boolean isCaptureNetworkTraffic;
    @JMap
    private String startedOn;
    @JMap
    private String completedOn;
    @JMap
    private String createdOn;
    @JMap
    private String modifiedOn;
    @JMap
    private String createdBy;
    @JMap
    private String modifiedBy;
    @JMap
    private String browserType;
    @JMap
    private String sessionId;
    @JMap
    private String resolution;
}
