package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.GetConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueType;

import java.util.List;

public interface ConfigKeyValueServiceProcessor {
    List<ErrorMessageDto> processorCreateConfigKeyValue(CreateConfigKeyValueDto createConfigKeyValueDto);

    List<ErrorMessageDto> createConfigKeyValue(CreateConfigKeyValueDto createConfigKeyValueDto);

    List<ErrorMessageDto> processorGetConfigKeyValue(GetConfigKeyValueDto getConfigKeyValueDto);

    List<ErrorMessageDto> getConfigKeyValue(GetConfigKeyValueDto getConfigKeyValueDto);

    Long createConfigKeyValue(Long appId, String key, String value, ConfigKeyValueType type, Long userId);
}
