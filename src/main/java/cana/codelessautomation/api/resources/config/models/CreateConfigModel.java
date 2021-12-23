package cana.codelessautomation.api.resources.config.models;

import lombok.Data;

@Data
public class CreateConfigModel {
    private String name;
    private String type;
    private Long userId;
}
