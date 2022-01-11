package cana.codelessautomation.api.resources.testplan.models;

import lombok.Data;

@Data
public class CopyTestPlanModel {
    private String name;
    private Long userId;
    private String comments;
}
