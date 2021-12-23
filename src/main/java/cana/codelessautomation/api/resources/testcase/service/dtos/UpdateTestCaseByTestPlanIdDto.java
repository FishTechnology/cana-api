package cana.codelessautomation.api.resources.testcase.service.dtos;

import lombok.Data;

@Data
public class UpdateTestCaseByTestPlanIdDto {
    private Long testPlanId;
    private Long testCaseId;
    private Long userId;
    private String name;
    private String comments;
}
