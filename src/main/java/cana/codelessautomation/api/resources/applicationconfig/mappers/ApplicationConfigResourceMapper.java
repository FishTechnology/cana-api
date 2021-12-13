package cana.codelessautomation.api.resources.applicationconfig.mappers;

import cana.codelessautomation.api.resources.applicationconfig.models.ApplicationConfigModel;
import cana.codelessautomation.api.resources.applicationconfig.models.CreateAppConfigModel;
import cana.codelessautomation.api.resources.applicationconfig.models.UpdateApplicationConfigModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.services.applicationconfig.dto.CreateAppConfigDto;
import cana.codelessautomation.api.services.applicationconfig.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.services.applicationconfig.repositories.daos.ApplicationConfigDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

import java.util.List;
import java.util.UUID;

public interface ApplicationConfigResourceMapper {
    CreateAppConfigDto mapCreateAppConfigDto(CreateAppConfigModel createAppConfigModel);

    ResultModel mapResultModel(CreateAppConfigDto createAppConfigDto, List<ErrorMessageDto> errorMessages);

    List<ApplicationConfigModel> mapApplicationConfigModels(List<ApplicationConfigDao> applicationConfigDaos);

    UpdateApplicationConfigDto mapCreateAppConfigDto(UUID applicationConfigId, UpdateApplicationConfigModel updateApplicationConfigmodel);
}
