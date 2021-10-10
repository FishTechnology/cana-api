package cana.codelessautomation.api.resources.environment.models;

import lombok.Data;

@Data
public class CreateEnvironmentModel {
    private String name;
    private Long userId;
    private String comments;
}
