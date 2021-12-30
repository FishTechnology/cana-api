package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.GetConfigKeyValueDto;

import java.util.List;

public interface ConfigKeyValueServiceProcessor {
    List<ErrorMessageDto> processorCreateConfigKeyValue(CreateConfigKeyValueDto createConfigKeyValueDto);

    List<ErrorMessageDto> createConfigKeyValue(CreateConfigKeyValueDto createConfigKeyValueDto);

    List<ErrorMessageDto> processorGetConfigKeyValue(GetConfigKeyValueDto getConfigKeyValueDto);

    List<ErrorMessageDto> getConfigKeyValue(GetConfigKeyValueDto getConfigKeyValueDto);
}
