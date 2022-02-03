package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.validator;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;

import java.util.List;

public interface ConfigKeyValueServiceValidator {
    List<ErrorMessageDto> validateCreateConfig(CreateConfigKeyValueDto createConfigDto);

    List<ErrorMessageDto> isKeyValid(CreateConfigKeyValueDto createConfigKeyValueDto);
}
