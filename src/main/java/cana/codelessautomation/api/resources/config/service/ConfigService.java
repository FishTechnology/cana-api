package cana.codelessautomation.api.resources.config.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.service.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.service.dtos.GetConfigsByUserIdDto;

import java.util.List;

public interface ConfigService {
    List<ErrorMessageDto> getConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto);

    List<ErrorMessageDto> createConfig(CreateConfigDto createConfigDto);
}
