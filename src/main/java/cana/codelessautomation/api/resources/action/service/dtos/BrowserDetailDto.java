package cana.codelessautomation.api.resources.action.service.dtos;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ConditionType;
import cana.codelessautomation.api.resources.action.service.repositories.daos.BrowserActionTypeDao;
import lombok.Data;

@Data
public class BrowserDetailDto {
    private BrowserActionTypeDao actionType;
    private String comments;
    private String value;
    private ConditionType conditionType;
}
