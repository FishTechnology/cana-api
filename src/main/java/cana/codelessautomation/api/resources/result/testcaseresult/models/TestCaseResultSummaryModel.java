package cana.codelessautomation.api.resources.result.testcaseresult.models;

import cana.codelessautomation.api.resources.result.actionresult.models.ActionResultSummaryModel;
import lombok.Data;

import java.util.List;

@Data
public class TestCaseResultSummaryModel {
    private Long id;
    private String testCaseName;
    private String duration;
    private String status;
    private String errorMessage;
    private Long executionOrder;
    private List<ActionResultSummaryModel> actionResults;
}
