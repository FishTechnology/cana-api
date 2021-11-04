package cana.codelessautomation.api.resources.action.models;

import lombok.Data;

@Data
public class ActionOptionModel {
    private String optionType;
    private Long waitDuration;
    private Long order;
}
