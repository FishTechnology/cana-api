package cana.codelessautomation.api.resources.result.testcaseresult.models;

import cana.codelessautomation.api.resources.result.actionresult.models.ActionResultModel;
import lombok.Data;

import java.util.List;

@Data
public class TestCaseResultModel {
    private Long id;
    private String testCaseName;
    private String duration;
    private String status;
    private String errorMessage;
    private Long executionOrder;
    private List<ActionResultModel> actionResults;
}
