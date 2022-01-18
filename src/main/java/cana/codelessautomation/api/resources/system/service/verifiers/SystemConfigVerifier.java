package cana.codelessautomation.api.resources.system.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.system.service.dtos.GetSystemConfigsByAppIdDto;

import java.util.List;

public interface SystemConfigVerifier {
    List<ErrorMessageDto> verifyGetSystemConfigsByAppId(GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto);

    List<ErrorMessageDto> isApplicationIdValid(GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto);
}
