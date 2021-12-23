package cana.codelessautomation.api.resources.applicationconfig.service.dto;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import lombok.Data;

@Data
public class CreateAppConfigDto {
    private Long applicationId;
    private Long id;
    private String key;
    private String value;
    private String userId;
    private Boolean isActive;
    private String createdBy;
    private String modifiedBy;
    private String comments;
    private CustomDetailDao customDetailDao;
    private ApplicationDao applicationDao;
}
