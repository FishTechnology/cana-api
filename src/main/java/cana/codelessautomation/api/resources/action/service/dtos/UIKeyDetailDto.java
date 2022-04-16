package cana.codelessautomation.api.resources.action.service.dtos;

import cana.codelessautomation.api.resources.action.service.repositories.daos.UIKey;
import lombok.Data;

@Data
public class UIKeyDetailDto {
    private Long orderNumber;
    private UIKey key;
    private Boolean isActive;
}
