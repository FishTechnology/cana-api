package cana.codelessautomation.api.resources.config.models;

import cana.codelessautomation.api.commons.models.BaseModel;
import lombok.Data;

@Data
public class ConfigKeyValueModel extends BaseModel {
    private String id;
    private String userId;
    private String configId;
    private String key;
    private String value;
    private String type;
    private String content;
    private String comments;
    private Boolean isActive = false;
    private Boolean isApplicationVariable;
}
