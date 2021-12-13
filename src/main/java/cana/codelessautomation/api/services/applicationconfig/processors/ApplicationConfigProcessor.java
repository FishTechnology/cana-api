package cana.codelessautomation.api.services.applicationconfig.processors;

import cana.codelessautomation.api.services.applicationconfig.dto.CreateAppConfigDto;
import cana.codelessautomation.api.services.applicationconfig.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

import java.util.List;
import java.util.UUID;

public interface ApplicationConfigProcessor {
    List<ErrorMessageDto> processCreateApplicationConfig(CreateAppConfigDto createAppConfigDto);

    List<ErrorMessageDto> processDeleteApplicationConfig(UUID applicationConfigId);

    List<ErrorMessageDto> deleteApplicationConfig(UUID applicationConfigId);

    List<ErrorMessageDto> processUpdateApplicationConfig(UpdateApplicationConfigDto updateApplicationConfigDto);

    List<ErrorMessageDto> updateApplicationConfig(UpdateApplicationConfigDto updateApplicationConfigDto);
}
