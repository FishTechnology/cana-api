package cana.codelessautomation.api.resources.config.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.service.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.service.dtos.GetConfigsByUserIdDto;

import java.util.List;

public interface ConfigServiceProcessor {
    List<ErrorMessageDto> processorGetConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto);

    List<ErrorMessageDto> getConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto);

    List<ErrorMessageDto> processorCreateConfig(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> createConfig(CreateConfigDto createConfigDto);
}
