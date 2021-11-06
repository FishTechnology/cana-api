package cana.codelessautomation.api.resources.result.testplanresult.models;

import cana.codelessautomation.api.resources.result.testcaseresult.models.TestCaseResultModel;
import lombok.Data;

import java.util.List;

@Data
public class TestPlanResultSummaryModel {
    private Long id;
    private String testPlanName;
    private String duration;
    private String status;
    private List<TestCaseResultModel> testCaseResults;
}
