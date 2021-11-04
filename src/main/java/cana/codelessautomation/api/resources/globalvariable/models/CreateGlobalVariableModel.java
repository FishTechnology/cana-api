package cana.codelessautomation.api.resources.globalvariable.models;

import lombok.Data;

import java.util.List;


@Data
public class CreateGlobalVariableModel {
    private String key;
    private String value;
    private String valueType;
    private Long fileId;
    private String comments;
    private Long userId;
    private List<UIControlOptionModel> uiControlOptions;
}
