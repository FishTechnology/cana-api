package cana.codelessautomation.api.resources.testcase.models;

import lombok.Data;

import java.util.List;

@Data
public class UpdateTestCaseOrderModel {
    private Long userId;
    private List<TestCaseOrderModel> testcaseOrderModels;
}
