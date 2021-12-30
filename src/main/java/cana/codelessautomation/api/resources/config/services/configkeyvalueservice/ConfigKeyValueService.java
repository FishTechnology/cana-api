package cana.codelessautomation.api.resources.config.services.configkeyvalueservice;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.GetConfigKeyValueDto;

import java.util.List;

public interface ConfigKeyValueService {

    List<ErrorMessageDto> createConfigKeyValue(CreateConfigKeyValueDto createConfigKeyValueDto);

    List<ErrorMessageDto> getConfigKeyValue(GetConfigKeyValueDto getConfigKeyValueDto);
}
