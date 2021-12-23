package cana.codelessautomation.api.resources.applicationconfig.service;

import cana.codelessautomation.api.resources.applicationconfig.service.dto.CreateAppConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.DeleteApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.GetApplicationConfigsDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.repositories.daos.ApplicationConfigDao;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;

import java.util.List;

public interface ApplicationConfigService {
    List<ErrorMessageDto> createApplicationConfig(CreateAppConfigDto createAppConfigDto);

    List<ApplicationConfigDao> getApplicationConfigs(GetApplicationConfigsDto getApplicationConfigsDto);

    List<ErrorMessageDto> deleteApplicationConfig(DeleteApplicationConfigDto deleteApplicationConfigDto);

    List<ErrorMessageDto> updateApplicationConfig(UpdateApplicationConfigDto updateApplicationConfigDto);
}
