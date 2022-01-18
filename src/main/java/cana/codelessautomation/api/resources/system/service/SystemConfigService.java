package cana.codelessautomation.api.resources.system.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.system.service.dtos.GetSystemConfigsByAppIdDto;

import java.util.List;

public interface SystemConfigService {
    List<ErrorMessageDto> getSystemConfigsByAppId(GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto);
}
