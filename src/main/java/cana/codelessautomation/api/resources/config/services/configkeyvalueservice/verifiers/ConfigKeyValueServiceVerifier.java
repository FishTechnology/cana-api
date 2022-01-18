package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.GetConfigKeyValueDto;

import java.util.List;

public interface ConfigKeyValueServiceVerifier {

    List<ErrorMessageDto> verifyCreateConfigKeyValue(CreateConfigKeyValueDto createConfigKeyValueDto);

    List<ErrorMessageDto> isApplicationIdValid(CreateConfigKeyValueDto createConfigKeyValueDto);

    List<ErrorMessageDto> isConfigKeyValid(CreateConfigKeyValueDto createConfigKeyValueDto);

    List<ErrorMessageDto> isUserIdValid(CreateConfigKeyValueDto createConfigKeyValueDto);

    List<ErrorMessageDto> isConfigIdValid(CreateConfigKeyValueDto createConfigKeyValueDto);

    List<ErrorMessageDto> verifyGetConfigKeyValue(GetConfigKeyValueDto getConfigKeyValueDto);

    List<ErrorMessageDto> isConfigKeyValid(GetConfigKeyValueDto getConfigKeyValueDto);
}
