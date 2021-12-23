package cana.codelessautomation.api.resources.applicationconfig.service.dto;

import cana.codelessautomation.api.resources.applicationconfig.service.repositories.daos.ApplicationConfigDao;
import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import lombok.Data;

@Data
public class UpdateApplicationConfigDto {
    private Long applicationId;
    private Long id;
    private String key;
    private String value;
    private String comments;
    private String userId;
    private CustomDetailDao customDetailDao;
    private ApplicationConfigDao applicationConfigDao;
}
