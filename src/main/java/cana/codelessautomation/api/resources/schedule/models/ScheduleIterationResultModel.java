package cana.codelessautomation.api.resources.schedule.models;

import cana.codelessautomation.api.resources.result.testplanresult.models.TestPlanResultSummaryModel;
import lombok.Data;

@Data
public class ScheduleIterationResultModel {
    private ScheduleModel schedule;
    private ScheduleIterationModel scheduleIteration;
    private TestPlanResultSummaryModel testPlanResultSummary;
}
