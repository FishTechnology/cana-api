package cana.codelessautomation.api.services.envvariable.dtos;

import lombok.Data;

@Data
public class UpdateEnvVariableDto {
    private String key;
    private String value;
    private Long userId;
    private String type;
    private String comments;
    private String content;
}
