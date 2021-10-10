package cana.codelessautomation.api.resources.envvariable.models;

import lombok.Data;

@Data
public class CreateEnvVariableModel {
    private String key;
    private String value;
    private Long userId;
    private Long environmentId;
    private String type;
    private String comments;
}
