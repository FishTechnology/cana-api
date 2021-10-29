package cana.codelessautomation.api.resources.testplan.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateTestplanModel {
    private Long userId;
    @NotEmpty(message = "")
    private String name;
    private String comments;
}
