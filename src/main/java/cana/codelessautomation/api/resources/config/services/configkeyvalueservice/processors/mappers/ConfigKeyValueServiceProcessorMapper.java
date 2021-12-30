package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.processors.mappers;

import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueDao;

public interface ConfigKeyValueServiceProcessorMapper {
    ConfigKeyValueDao mapConfigKeyValueDao(CreateConfigKeyValueDto createConfigKeyValueDto);
}
