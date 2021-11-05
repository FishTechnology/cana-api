package cana.codelessautomation.api.resources.schedule.models;

import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleStatusDao;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
public class ScheduleIterationModel {
    @Id
    private Long id;
    @JMap
    private Long scheduleId;
    @JMap
    @Enumerated(EnumType.STRING)
    private ScheduleStatusDao status;
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
}
