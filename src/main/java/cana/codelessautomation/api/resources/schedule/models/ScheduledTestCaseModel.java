package cana.codelessautomation.api.resources.schedule.models;

import cana.codelessautomation.api.resources.testcase.models.TestCaseModel;
import lombok.Data;

import java.util.List;

@Data
public class ScheduledTestCaseModel extends TestCaseModel {
    private Long order;
    private List<ScheduledActionDetailModel> scheduledActionDetails;
}
