package cana.codelessautomation.api.resources.result.actionresult.models;

import lombok.Data;

@Data
public class ActionResultModel {
    private Long id;
    private String key;
    private String value;
    private String duration;
    private String status;
    private String errorMessage;
    private Long executionOrder;
}
