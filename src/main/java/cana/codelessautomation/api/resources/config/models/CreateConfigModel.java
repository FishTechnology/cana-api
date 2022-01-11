package cana.codelessautomation.api.resources.config.models;

import lombok.Data;

@Data
public class CreateConfigModel {
    private String name;
    private Long userId;
    private String comments;
    private String identifier;
}
