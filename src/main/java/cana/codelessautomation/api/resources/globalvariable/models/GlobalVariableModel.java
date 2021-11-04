package cana.codelessautomation.api.resources.globalvariable.models;

import com.googlecode.jmapper.annotations.JMap;
import lombok.*;

import java.time.OffsetDateTime;

@Data
public class GlobalVariableModel {
    @JMap
    private Long id;
    @JMap
    private String key;
    @JMap
    private String value;
    @JMap
    private String valueType;
    @JMap
    private String content;
    private Long fileId;
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
}
