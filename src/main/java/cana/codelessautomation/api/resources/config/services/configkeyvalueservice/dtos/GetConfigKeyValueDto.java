package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos;

import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueDao;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import lombok.Data;

import java.util.List;

@Data
public class GetConfigKeyValueDto {
    private Long configId;
    private ConfigDao configDao;
    private List<ConfigKeyValueDao> configKeyValueDaos;
}
