package cana.codelessautomation.api.resources.action.models;

import cana.codelessautomation.api.services.action.repositories.daos.ActionTypeDao;
import lombok.Data;

import java.util.List;

@Data
public class ActionDetailModel {
    private Long id;
    private ActionTypeDao type;
    private String key;
    private String value;
    private String uiActionType;
    private String comments;
    private Long userId;
    private Long testCaseId;
    private Long order;
    private String createdOn;
    private String modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private String browserActionType;
    private String url;
    private Boolean isActive;
    private List<ActionOptionModel> actionOptionModels;
}