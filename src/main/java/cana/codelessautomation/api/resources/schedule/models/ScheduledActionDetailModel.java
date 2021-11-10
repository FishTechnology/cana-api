package cana.codelessautomation.api.resources.schedule.models;

import cana.codelessautomation.api.resources.action.models.ActionDetailModel;
import lombok.Data;

import java.util.List;

@Data
public class ScheduledActionDetailModel extends ActionDetailModel {
    private List<ScheduledActionOptionModel> scheduledActionOptions;
}
