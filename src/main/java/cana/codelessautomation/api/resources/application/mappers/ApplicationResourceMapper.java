package cana.codelessautomation.api.resources.application.mappers;

import cana.codelessautomation.api.resources.application.models.ApplicationModel;
import cana.codelessautomation.api.resources.application.models.CreateAppModel;
import cana.codelessautomation.api.resources.application.models.UpdateApplicationModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.services.application.dtos.CreateApplicationDto;
import cana.codelessautomation.api.services.application.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.services.application.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.services.application.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

import java.util.List;

public interface ApplicationResourceMapper {
    CreateApplicationDto mapCreateAppDto(CreateAppModel createAppModel);

    ResultModel mapResultModel(CreateApplicationDto createApplicationDto, List<ErrorMessageDto> errorMessages);

    List<ApplicationModel> mapApplicationModels(List<ApplicationDao> applicationDaos);

    DeleteApplicationDto mapDeleteApplicationDto(Long applicationId);

    UpdateApplicationDto mapUpdateApplicationDto(Long applicationId, UpdateApplicationModel updateApplicationModel);

    ApplicationModel mapApplicationModel(ApplicationDao applicationDao);
}
