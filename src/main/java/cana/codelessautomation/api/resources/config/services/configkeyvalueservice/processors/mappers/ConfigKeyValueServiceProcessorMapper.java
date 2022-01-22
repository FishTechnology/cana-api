package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.processors.mappers;

import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueDao;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueType;

public interface ConfigKeyValueServiceProcessorMapper {
    ConfigKeyValueDao mapConfigKeyValueDao(CreateConfigKeyValueDto createConfigKeyValueDto);

    ConfigKeyValueDao mapConfigKeyValueDao(Long configId, String key, String value, ConfigKeyValueType type, Long userId);
}
