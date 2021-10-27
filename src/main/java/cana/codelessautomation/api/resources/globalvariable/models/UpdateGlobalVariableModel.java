package cana.codelessautomation.api.resources.globalvariable.models;

import lombok.Data;

@Data
public class UpdateGlobalVariableModel {
    private String key;
    private String value;
    private String valueType;
    private String comments;
    private Long userId;
}
