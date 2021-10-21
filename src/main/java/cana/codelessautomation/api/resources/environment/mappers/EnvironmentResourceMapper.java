package cana.codelessautomation.api.resources.environment.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.environment.models.CreateEnvironmentModel;
import cana.codelessautomation.api.resources.environment.models.DeleteEnvironmentsModel;
import cana.codelessautomation.api.resources.environment.models.EnvironmentModel;
import cana.codelessautomation.api.resources.environment.models.UpdateEnvironmentModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.services.environment.dtos.DeleteEnvironmentsDto;
import cana.codelessautomation.api.services.environment.dtos.UpdateEnvironmentDto;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;

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
