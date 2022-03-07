package cana.codelessautomation.api.resources.action.models;

import lombok.Data;

@Data
public class ActionOptionModel {
    private Long id;
    private Long actionId;
    private String optionType;
    private Long order;
    private String controlType;
    private String createdOn;
    private String modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private Boolean isActive;
    private String value;
    private String contentType;
    private Long duration;
}
