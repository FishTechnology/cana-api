package cana.codelessautomation.api.resources.applicationconfig.models;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

@Data
public class ApplicationConfigModel {
    @JMap
    private Long id;
    @JMap
    private String key;
    @JMap
    private String value;
    @JMap
    private Long userId;
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
    @JMap
    private String comments;
}
