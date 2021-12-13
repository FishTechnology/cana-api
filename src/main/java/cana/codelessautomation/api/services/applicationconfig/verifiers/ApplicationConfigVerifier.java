package cana.codelessautomation.api.services.applicationconfig.verifiers;

import cana.codelessautomation.api.services.applicationconfig.dto.CreateAppConfigDto;
import cana.codelessautomation.api.services.applicationconfig.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

import java.util.List;
import java.util.UUID;

public interface ApplicationConfigVerifier {
    List<ErrorMessageDto> verifyCreateApplicationConfig(CreateAppConfigDto createAppConfigDto);

    List<ErrorMessageDto> isKeyValid(CreateAppConfigDto createAppConfigDto);

    List<ErrorMessageDto> isUserIdValid(CreateAppConfigDto createAppConfigDto);

    List<ErrorMessageDto> verifyGetApplicationConfigs(String userId);

    List<ErrorMessageDto> verifyDeleteApplicationConfig(UUID applicationConfigId);

    List<ErrorMessageDto> isApplicationConfigIdValid(UUID applicationConfigId);

    List<ErrorMessageDto> verifyUpdateApplicationConfig(UpdateApplicationConfigDto updateApplicationConfigDto);

    List<ErrorMessageDto> isKeyValid(UpdateApplicationConfigDto updateApplicationConfigDto);

    List<ErrorMessageDto> isApplicationConfigIdValid(UpdateApplicationConfigDto updateApplicationConfigDto);

    List<ErrorMessageDto> isUserIdValid(UpdateApplicationConfigDto updateApplicationConfigDto);
}
