package cana.codelessautomation.api.resources.schedule.models;

import cana.codelessautomation.api.resources.testplan.models.TestPlanModel;
import lombok.Data;

import java.util.List;

@Data
public class ScheduleTestPlanModel extends TestPlanModel {
    private List<ScheduledTestCaseModel> scheduledTestCaseModel;
}
