package cana.codelessautomation.api.resources.config.models;

import lombok.Data;

@Data
public class CreateConfigKeyValueModel {
    private String key;
    private String value;
    private String type;
    private Long fileId;
    private String comments;
    private Long userId;
}
