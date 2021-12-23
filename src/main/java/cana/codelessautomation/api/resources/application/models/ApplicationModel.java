package cana.codelessautomation.api.resources.application.models;

import lombok.Data;

@Data
public class ApplicationModel {
    private String id;
    private String name;
    private String userId;
    private String createdOn;
    private String modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private Boolean isActive = false;
    private String comments;
}
