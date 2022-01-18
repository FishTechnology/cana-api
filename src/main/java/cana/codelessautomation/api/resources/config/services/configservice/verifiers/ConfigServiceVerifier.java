package cana.codelessautomation.api.resources.config.services.configservice.verifiers;


import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByAppIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;

import java.util.List;

public interface ConfigServiceVerifier {
    List<ErrorMessageDto> verifyGetConfigsByUserId(GetConfigsByAppIdDto getConfigsByAppIdDto);

    List<ErrorMessageDto> isApplicationIdValid(GetConfigByIdDto getConfigByIdDto);

    List<ErrorMessageDto> checkConfigTypeDuplicate(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> checkConfigTypeWithIdentifierDuplicate(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> isUserIdValid(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> isUserIdValid(GetConfigsByAppIdDto getConfigsByAppIdDto);

    List<ErrorMessageDto> verifyCreateConfig(CreateConfigDto createConfigDto);

    KeyValue<List<ErrorMessageDto>, ConfigDao> isConfigIdValid(Long configId);

    List<ErrorMessageDto> isApplicationIdValid(CreateConfigDto createConfigDto);

    List<ErrorMessageDto> verifyGetConfigById(GetConfigByIdDto getConfigByIdDto);
}
