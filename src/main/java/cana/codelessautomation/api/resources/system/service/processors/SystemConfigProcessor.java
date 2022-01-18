package cana.codelessautomation.api.resources.system.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.system.service.dtos.GetSystemConfigsByAppIdDto;

import java.util.List;

public interface SystemConfigProcessor {
    List<ErrorMessageDto> processorGetSystemConfigsByAppId(GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto);

    List<ErrorMessageDto> getSystemConfigsByAppId(GetSystemConfigsByAppIdDto getSystemConfigsByAppIdDto);

    List<ErrorMessageDto> createInitialConfig(Long applicationId, Long userId);
}
