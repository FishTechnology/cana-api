package cana.codelessautomation.api.resources.testcase.models;

import lombok.Data;

@Data
public class UpdateTestCaseModel {
    private Long userId;
    private String name;
    private String comments;
}
