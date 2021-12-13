package cana.codelessautomation.api.services.applicationconfig.dto;

import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateAppConfigDto {
    private UUID id;
    private String key;
    private String value;
    private String userId;
    private Boolean isActive;
    private String createdBy;
    private String modifiedBy;
    private String comments;
    private CustomDetailDao customDetailDao;
}
