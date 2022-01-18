package cana.codelessautomation.api.resources.config.services.configservice.dtos;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigTypeDao;
import lombok.Data;

@Data
public class GetConfigByIdDto {
    private Long configId;
    private Long applicationId;
    private ApplicationDao application;
    private ConfigDao configDao;
    private ConfigTypeDao configType;
}
