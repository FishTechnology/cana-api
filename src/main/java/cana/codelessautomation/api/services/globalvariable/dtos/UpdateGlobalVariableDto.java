package cana.codelessautomation.api.services.globalvariable.dtos;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UpdateGlobalVariableDto {
    private Long globalVariableId;
    @JMap
    private String key;
    @JMap
    private String value;
    @JMap
    private String valueType;
    @JMap
    private String content;
    @JMap
    private String comments;
    @JMap
    private Long userId;
    private Boolean isActive;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
}
