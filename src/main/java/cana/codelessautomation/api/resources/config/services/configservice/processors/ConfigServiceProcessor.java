package cana.codelessautomation.api.resources.config.services.configservice.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByUserIdDto;

import java.util.List;

public interface ConfigServiceProcessor {
    List<ErrorMessageDto> processorGetConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto);

    List<ErrorMessageDto> getConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto);

    List<ErrorMessageDto> processorCreateConfig(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> createConfig(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> processorGetConfigById(GetConfigByIdDto getConfigByIdDto);

    List<ErrorMessageDto> getConfigById(GetConfigByIdDto getConfigByIdDto);
}
