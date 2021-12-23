package cana.codelessautomation.api.resources.config.service.verifiers;


import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.service.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.service.dtos.GetConfigsByUserIdDto;

import java.util.List;

public interface ConfigServiceVerifier {
    List<ErrorMessageDto> verifyGetConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto);

    List<ErrorMessageDto> isUserIdValid(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> isUserIdValid(GetConfigsByUserIdDto getConfigsByUserIdDto);

    List<ErrorMessageDto> verifyCreateConfig(CreateConfigDto createConfigDto);
}
