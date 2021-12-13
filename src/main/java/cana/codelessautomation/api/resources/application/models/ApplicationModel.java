package cana.codelessautomation.api.resources.application.models;

import lombok.Data;

@Data
public class ApplicationModel {
    private Long id;
    private String name;
    private Long userId;
    private String createdOn;
    private String modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private Boolean isActive = false;
    private String comments;
}
