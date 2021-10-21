package cana.codelessautomation.api.resources.testplan.models;

import lombok.Data;

@Data
public class CreateTestplanModel {
    private Long userId;
    private String name;
    private String comments;
}
