package cana.codelessautomation.api.resources.result.testcaseresult.models;

import lombok.Data;

@Data
public class TestCaseResultModel {
    private Long id;
    private Long testCaseId;
    private String startedOn;
    private String completedOn;
    private String duration;
    private String status;
    private String errorMessage;
    private String totalDuration;
    private Long executionOrder;
    private Long testPlanResultId;
    private String createdOn;
    private String modifiedOn;
}
