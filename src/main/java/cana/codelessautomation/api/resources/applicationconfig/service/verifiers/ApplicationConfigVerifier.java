package cana.codelessautomation.api.resources.applicationconfig.service.verifiers;

import cana.codelessautomation.api.resources.applicationconfig.service.dto.CreateAppConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.DeleteApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.GetApplicationConfigsDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;

import java.util.List;

public interface ApplicationConfigVerifier {
    List<ErrorMessageDto> verifyCreateApplicationConfig(CreateAppConfigDto createAppConfigDto);

    List<ErrorMessageDto> isApplicationIdValid(CreateAppConfigDto createAppConfigDto);

    List<ErrorMessageDto> isKeyValid(CreateAppConfigDto createAppConfigDto);

    List<ErrorMessageDto> isUserIdValid(CreateAppConfigDto createAppConfigDto);

    List<ErrorMessageDto> verifyGetApplicationConfigs(GetApplicationConfigsDto getApplicationConfigsDto);

    List<ErrorMessageDto> isApplicationIdValid(GetApplicationConfigsDto getApplicationConfigsDto);

    List<ErrorMessageDto> isUserIdValid(GetApplicationConfigsDto getApplicationConfigsDto);

    List<ErrorMessageDto> verifyDeleteApplicationConfig(DeleteApplicationConfigDto deleteApplicationConfigDto);

    List<ErrorMessageDto> isApplicationConfigIdValid(DeleteApplicationConfigDto deleteApplicationConfigDto);

    List<ErrorMessageDto> verifyUpdateApplicationConfig(UpdateApplicationConfigDto updateApplicationConfigDto);

    List<ErrorMessageDto> isKeyValid(UpdateApplicationConfigDto updateApplicationConfigDto);

    List<ErrorMessageDto> isApplicationConfigIdValid(UpdateApplicationConfigDto updateApplicationConfigDto);

    List<ErrorMessageDto> isUserIdValid(UpdateApplicationConfigDto updateApplicationConfigDto);
}
