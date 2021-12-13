package cana.codelessautomation.api.resources.application.models;

import lombok.Data;

@Data
public class UpdateApplicationModel {
    private String name;
    private String comments;
    private Long userId;
}
