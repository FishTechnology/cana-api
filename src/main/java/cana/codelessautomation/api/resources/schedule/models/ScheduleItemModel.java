package cana.codelessautomation.api.resources.schedule.models;

import lombok.Data;

@Data
public class ScheduleItemModel {
    private Long scheduleId;
    private String testplanName;
    private String environmentName;
    private String status;
    private String lastExecute;
}
