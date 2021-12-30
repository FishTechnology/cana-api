package cana.codelessautomation.api.resources.config.services.configservice.processors.mappers;

import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;

public interface ConfigServiceProcessorMapper {
    ConfigDao mapConfigDao(CreateConfigDto createConfigDto);
}
