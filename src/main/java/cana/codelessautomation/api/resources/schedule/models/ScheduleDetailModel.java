package cana.codelessautomation.api.resources.schedule.models;

import lombok.Data;

@Data
public class ScheduleDetailModel {
    private ScheduleModel scheduleModel;
    private ScheduleIterationModel scheduleIterationModel;
    private ScheduleTestPlanModel scheduleTestPlanModel;
}
