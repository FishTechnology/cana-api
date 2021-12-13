package cana.codelessautomation.api.services.applicationconfig;

import cana.codelessautomation.api.services.applicationconfig.dto.CreateAppConfigDto;
import cana.codelessautomation.api.services.applicationconfig.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.services.applicationconfig.repositories.daos.ApplicationConfigDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

import java.util.List;
import java.util.UUID;

public interface ApplicationConfigService {
    List<ErrorMessageDto> createApplicationConfig(CreateAppConfigDto createAppConfigDto);

    List<ApplicationConfigDao> getApplicationConfigs(String userId);

    List<ErrorMessageDto> deleteApplicationConfig(UUID applicationConfigId);

    List<ErrorMessageDto> updateApplicationConfig(UpdateApplicationConfigDto updateApplicationConfigDto);
}
