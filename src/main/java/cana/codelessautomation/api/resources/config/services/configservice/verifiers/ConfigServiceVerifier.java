package cana.codelessautomation.api.resources.config.services.configservice.verifiers;


import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByAppIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByConfigTypeDto;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;

import java.util.List;

public interface ConfigServiceVerifier {
    List<ErrorMessageDto> verifyGetConfigsByUserId(GetConfigsByConfigTypeDto getConfigsByConfigTypeDto);

    List<ErrorMessageDto> isApplicationIdValid(GetConfigByIdDto getConfigByIdDto);

    List<ErrorMessageDto> checkConfigTypeDuplicate(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> checkConfigTypeWithIdentifierDuplicate(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> isUserIdValid(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> isUserIdValid(GetConfigsByConfigTypeDto getConfigsByConfigTypeDto);

    List<ErrorMessageDto> verifyCreateConfig(CreateConfigDto createConfigDto);

    KeyValue<List<ErrorMessageDto>, ConfigDao> isConfigIdValid(Long configId);

    List<ErrorMessageDto> isApplicationIdValid(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> isApplicationIdValid(GetConfigsByAppIdDto getConfigsByAppIdDto);

    List<ErrorMessageDto> verifyGetConfigById(GetConfigByIdDto getConfigByIdDto);

    List<ErrorMessageDto> verifyGetConfigsByAppId(GetConfigsByAppIdDto getConfigsByAppIdDto);
}
