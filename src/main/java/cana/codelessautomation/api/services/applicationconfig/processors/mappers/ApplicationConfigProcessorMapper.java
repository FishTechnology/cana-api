package cana.codelessautomation.api.services.applicationconfig.processors.mappers;

import cana.codelessautomation.api.services.applicationconfig.dto.CreateAppConfigDto;
import cana.codelessautomation.api.services.applicationconfig.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.services.applicationconfig.repositories.daos.ApplicationConfigDao;

public interface ApplicationConfigProcessorMapper {
    ApplicationConfigDao mapApplicationConfigDao(CreateAppConfigDto createAppConfigDto);

    ApplicationConfigDao mapApplicationConfigDao(UpdateApplicationConfigDto updateApplicationConfigDto);
}
