package cana.codelessautomation.api.resources.applicationconfig.mappers;

import cana.codelessautomation.api.resources.applicationconfig.models.ApplicationConfigModel;
import cana.codelessautomation.api.resources.applicationconfig.models.CreateAppConfigModel;
import cana.codelessautomation.api.resources.applicationconfig.models.UpdateApplicationConfigModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.CreateAppConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.DeleteApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.GetApplicationConfigsDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.repositories.daos.ApplicationConfigDao;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;

import java.util.List;

public interface ApplicationConfigResourceMapper {
    CreateAppConfigDto mapCreateAppConfigDto(Long applicationId, CreateAppConfigModel createAppConfigModel);

    ResultModel mapResultModel(CreateAppConfigDto createAppConfigDto, List<ErrorMessageDto> errorMessages);

    List<ApplicationConfigModel> mapApplicationConfigModels(List<ApplicationConfigDao> applicationConfigDaos);

    GetApplicationConfigsDto mapGetApplicationConfigsDto(Long applicationId, Long userId);

    DeleteApplicationConfigDto mapDeleteApplicationConfigDto(Long applicationId, Long applicationConfigId);

    UpdateApplicationConfigDto mapUpdateApplicationConfigDto(Long applicationId, Long applicationConfigId, UpdateApplicationConfigModel updateApplicationConfigmodel);
}
