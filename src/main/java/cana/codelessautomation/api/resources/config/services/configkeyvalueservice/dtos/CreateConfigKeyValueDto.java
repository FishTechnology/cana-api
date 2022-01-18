package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueType;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import lombok.Data;

@Data
public class CreateConfigKeyValueDto {
    private Long id;
    private Long configId;
    private String key;
    private String value;
    private ConfigKeyValueType type;
    private Long fileId;
    private String comments;
    private Long userId;
    private CustomDetailDao customDetailDao;
    private ConfigDao configDao;
    private Long applicationId;
    private ApplicationDao application;
}
