package cana.codelessautomation.api.resources.schedule.models;

import lombok.Data;

import java.util.List;

@Data
public class SchedulePageModel {
    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private List<ScheduleItemModel> scheduleItem;
}
