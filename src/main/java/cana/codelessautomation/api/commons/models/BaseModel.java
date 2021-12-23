package cana.codelessautomation.api.commons.models;

import lombok.Data;

@Data()
public class BaseModel {
    private String createdOn;
    private String modifiedOn;
    private String createdBy;
    private String modifiedBy;
}
