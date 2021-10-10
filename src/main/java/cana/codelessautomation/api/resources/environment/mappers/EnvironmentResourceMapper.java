package cana.codelessautomation.api.resources.environment.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.environment.models.CreateEnvironmentModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;

import java.util.List;

public interface EnvironmentResourceMapper {
    CreateEnvironmentDto mapCreateEnvVariableDto(CreateEnvironmentModel createEnvVariableModel);
    ResultModel mapResultModel(CreateEnvironmentDto createEnvironmentDto, List<ErrorMessageDto> errorMessages);
}
