package cana.codelessautomation.api.resources.schedule.models;

import lombok.Data;

@Data
public class ScheduleItemModel {
    private String scheduleId;
    private String testplanName;
    private String environmentName;
    private String status;
    private String lastExecute;
}
