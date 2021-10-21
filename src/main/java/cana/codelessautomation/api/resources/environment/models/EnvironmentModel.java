package cana.codelessautomation.api.resources.environment.models;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

@Data
public class EnvironmentModel {
    @JMap
    private Long id;
    @JMap
    private Long userId;
    @JMap
    private String name;
    @JMap
    private String comments;
    @JMap
    private String createdOn;
    @JMap
    private String modifiedOn;
    @JMap
    private String createdBy;
    @JMap
    private String modifiedBy;
    @JMap
    private Boolean isActive;
}
