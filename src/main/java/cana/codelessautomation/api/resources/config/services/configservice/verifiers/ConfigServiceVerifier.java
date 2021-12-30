package cana.codelessautomation.api.resources.config.services.configservice.verifiers;


import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByUserIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;

import java.util.List;

public interface ConfigServiceVerifier {
    List<ErrorMessageDto> verifyGetConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto);

    List<ErrorMessageDto> checkConfigTypeDuplicate(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> isUserIdValid(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> isUserIdValid(GetConfigsByUserIdDto getConfigsByUserIdDto);

    List<ErrorMessageDto> verifyCreateConfig(CreateConfigDto createConfigDto);

    KeyValue<List<ErrorMessageDto>, ConfigDao> isConfigIdValid(Long configId);
}
