package cana.codelessautomation.api.resources.action.models;

import lombok.Data;

@Data
public class ActionOrderModel {
    private Long actionId;
    private Long oldExecutionOrder;
    private Long currentExecutionOrder;
}
