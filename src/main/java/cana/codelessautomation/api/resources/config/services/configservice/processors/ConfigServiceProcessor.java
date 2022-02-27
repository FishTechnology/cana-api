package cana.codelessautomation.api.resources.config.services.configservice.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByAppIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByConfigTypeDto;

import java.util.List;

public interface ConfigServiceProcessor {
    List<ErrorMessageDto> processorGetConfigsByAppId(GetConfigsByConfigTypeDto getConfigsByConfigTypeDto);

    List<ErrorMessageDto> getConfigsByUserId(GetConfigsByConfigTypeDto getConfigsByConfigTypeDto);

    List<ErrorMessageDto> getConfigByType(GetConfigsByConfigTypeDto getConfigsByConfigTypeDto);

    List<ErrorMessageDto> processorCreateConfig(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> createConfig(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> getConfigsByAppId(GetConfigsByAppIdDto getConfigsByAppIdDto);

    List<ErrorMessageDto> processorGetConfigById(GetConfigByIdDto getConfigByIdDto);

    List<ErrorMessageDto> getConfigById(GetConfigByIdDto getConfigByIdDto);

    Long createConfig(String name, String type, String value, Long applicationId, Long userId);

    List<ErrorMessageDto> processorGetConfigsByAppId(GetConfigsByAppIdDto getConfigsByAppIdDto);
}
