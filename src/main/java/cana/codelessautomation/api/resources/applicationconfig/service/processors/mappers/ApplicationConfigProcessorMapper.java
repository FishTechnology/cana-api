package cana.codelessautomation.api.resources.applicationconfig.service.processors.mappers;

import cana.codelessautomation.api.resources.applicationconfig.service.dto.CreateAppConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.repositories.daos.ApplicationConfigDao;

public interface ApplicationConfigProcessorMapper {
    ApplicationConfigDao mapApplicationConfigDao(CreateAppConfigDto createAppConfigDto);

    ApplicationConfigDao mapApplicationConfigDao(UpdateApplicationConfigDto updateApplicationConfigDto);
}
