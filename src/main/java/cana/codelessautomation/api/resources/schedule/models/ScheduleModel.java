package cana.codelessautomation.api.resources.schedule.models;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.Id;

@Data
public class ScheduleModel {
    @Id
    private String id;
    @JMap
    private String testPlanId;
    @JMap
    private String environmentId;
    @JMap
    private String applicationId;
    @JMap
    private String userId;
    @JMap
    private String status;
    @JMap
    private String createdOn;
    @JMap
    private String modifiedOn;
    @JMap
    private String createdBy;
    @JMap
    private String modifiedBy;
    @JMap
    private ScheduleIterationModel scheduleIteration;
}
