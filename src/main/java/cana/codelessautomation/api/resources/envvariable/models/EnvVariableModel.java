package cana.codelessautomation.api.resources.envvariable.models;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class EnvVariableModel {
    @JMap
    private Long id;
    @JMap
    private String key;
    @JMap
    private String value;
    @JMap
    private String type;
    @JMap
    private OffsetDateTime createdOn;
    @JMap
    private OffsetDateTime modifiedOn;
    @JMap
    private String createdBy;
    @JMap
    private String modifiedBy;
    @JMap
    private Boolean isActive;
    @JMap
    private String comments;
    @JMap
    private Long userId;
    @JMap
    private Long environmentId;
    @JMap
    private String content;
}
