package cana.codelessautomation.api.resources.config.service.processors.mappers;

import cana.codelessautomation.api.resources.config.service.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.service.repositories.daos.ConfigDao;

public interface ConfigServiceProcessorMapper {
    ConfigDao mapConfigDao(CreateConfigDto createConfigDto);
}
