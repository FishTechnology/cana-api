package cana.codelessautomation.api.services.action.dtos;

import cana.codelessautomation.api.services.action.repositories.daos.BrowserActionTypeDao;
import cana.codelessautomation.api.services.action.repositories.daos.ConditionType;
import lombok.Data;

@Data
public class BrowserDetailDto {
    private BrowserActionTypeDao actionType;
    private String comments;
    private String value;
    private ConditionType conditionType;
}
