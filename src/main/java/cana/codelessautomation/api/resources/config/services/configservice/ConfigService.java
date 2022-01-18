package cana.codelessautomation.api.resources.config.services.configservice;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByAppIdDto;

import java.util.List;

public interface ConfigService {
    List<ErrorMessageDto> getConfigsByAppId(GetConfigsByAppIdDto getConfigsByAppIdDto);

    List<ErrorMessageDto> createConfig(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> getConfigById(GetConfigByIdDto getConfigByIdDto);
}
