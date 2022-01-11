package cana.codelessautomation.api.resources.testcase.models;

import lombok.Data;

@Data
public class TestCaseOrderModel {
    private Long testCaseId;
    private Long oldExecutionOrder;
    private Long currentExecutionOrder;
}
