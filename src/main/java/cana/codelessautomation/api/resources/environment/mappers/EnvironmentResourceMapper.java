package cana.codelessautomation.api.resources.environment.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.environment.models.CreateEnvironmentModel;
import cana.codelessautomation.api.resources.environment.models.DeleteEnvironmentsModel;
import cana.codelessautomation.api.resources.environment.models.EnvironmentModel;
import cana.codelessautomation.api.resources.environment.models.UpdateEnvironmentModel;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.environment.service.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.DeleteEnvironmentsDto;
import cana.codelessautomation.api.resources.environment.service.dtos.UpdateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;

import java.util.List;

public interface EnvironmentResourceMapper {
    CreateEnvironmentDto mapCreateEnvVariableDto(CreateEnvironmentModel createEnvVariableModel);

    ResultModel mapResultModel(CreateEnvironmentDto createEnvironmentDto, List<ErrorMessageDto> errorMessages);

    List<EnvironmentModel> mapEnvironmentModels(List<EnvironmentDao> environments);

    DeleteEnvironmentDto mapDeleteEnvironmentDto(Long environmentId);

    DeleteEnvironmentsDto mapDeleteEnvironmentsDto(DeleteEnvironmentsModel deleteEnvironmentsModel);

    EnvironmentModel mapEnvironmentModel(EnvironmentDao environment);

    UpdateEnvironmentDto mapUpdateEnvironmentDto(UpdateEnvironmentModel updateEnvVariableModel, Long environmentId);
}
