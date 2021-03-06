package cana.codelessautomation.api.resources.config.models;

import cana.codelessautomation.api.commons.models.BaseModel;
import lombok.Data;

import java.util.List;

@Data
public class ConfigModel extends BaseModel {
    private String id;
    private String name;
    private String type;
    private String userId;
    private String createdOn;
    private String modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private String comments;
    private String identifier;
    private String applicationId;
    private Boolean isActive = false;
    private List<ConfigKeyValueModel> configKeyValues;
}
