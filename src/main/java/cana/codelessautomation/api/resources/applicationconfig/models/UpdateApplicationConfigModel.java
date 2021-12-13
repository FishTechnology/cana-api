package cana.codelessautomation.api.resources.applicationconfig.models;

import lombok.Data;

@Data
public class UpdateApplicationConfigModel {
    private String key;
    private String value;
    private String comments;
    private String userId;
}
