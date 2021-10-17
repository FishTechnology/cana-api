package cana.codelessautomation.api.resources.globalvariable.models;

import lombok.Data;

@Data
public class CreateGlobalVariableModel {
    private String key;
    private String value;
    private String valueType;
    private String content;
    private Boolean isActive;
    private String comments;
    private Long userId;
}