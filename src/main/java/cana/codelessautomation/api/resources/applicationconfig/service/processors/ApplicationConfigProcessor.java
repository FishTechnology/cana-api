package cana.codelessautomation.api.resources.applicationconfig.service.processors;

import cana.codelessautomation.api.resources.applicationconfig.service.dto.CreateAppConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.DeleteApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.GetApplicationConfigsDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.repositories.daos.ApplicationConfigDao;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;

import java.util.List;

public interface ApplicationConfigProcessor {
    List<ErrorMessageDto> processCreateApplicationConfig(CreateAppConfigDto createAppConfigDto);

    List<ErrorMessageDto> processDeleteApplicationConfig(DeleteApplicationConfigDto deleteApplicationConfigDto);

    List<ErrorMessageDto> deleteApplicationConfig(DeleteApplicationConfigDto deleteApplicationConfigDto);

    List<ErrorMessageDto> processUpdateApplicationConfig(UpdateApplicationConfigDto updateApplicationConfigDto);

    List<ErrorMessageDto> updateApplicationConfig(UpdateApplicationConfigDto updateApplicationConfigDto);

    List<ApplicationConfigDao> processGetApplicationConfigs(GetApplicationConfigsDto getApplicationConfigsDto);

    List<ApplicationConfigDao> getApplicationConfigs(GetApplicationConfigsDto getApplicationConfigsDto);
}
