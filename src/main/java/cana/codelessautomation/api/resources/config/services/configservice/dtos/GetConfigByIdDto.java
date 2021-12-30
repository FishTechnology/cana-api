package cana.codelessautomation.api.resources.config.services.configservice.dtos;

import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigTypeDao;
import lombok.Data;

@Data
public class GetConfigByIdDto {
    private Long configId;
    private ConfigDao configDao;
    private ConfigTypeDao configType;
}
