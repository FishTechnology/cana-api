package cana.codelessautomation.api.resources.testplan.models;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TestPlanModel {
    @JMap
    private String id;
    @JMap
    private String userId;
    @JMap
    private String name;
    @JMap
    private String comments;
    @JMap
    private OffsetDateTime createdOn;
    @JMap
    private OffsetDateTime modifiedOn;
    @JMap
    private String createdBy;
    @JMap
    private String modifiedBy;
    @JMap
    private String status;
}
