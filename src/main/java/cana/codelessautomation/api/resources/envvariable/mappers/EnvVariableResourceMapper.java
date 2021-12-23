package cana.codelessautomation.api.resources.envvariable.mappers;

import cana.codelessautomation.api.resources.commonmodels.PageSetDetailModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.envvariable.models.CreateEnvVariableModel;
import cana.codelessautomation.api.resources.envvariable.models.EnvVariableModel;
import cana.codelessautomation.api.resources.envvariable.models.UpdateEnvVariableModel;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.customer.service.dtos.EnvPageSetDetailDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.GetEnvVariableByIdDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.UpdateEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableDao;

import java.util.List;

public interface EnvVariableResourceMapper {
    PageSetDetailModel mapPageSetDetailModel(EnvPageSetDetailDto envPageSetDetailDto);

    DeleteEnvVariableDto mapDeleteEnvVariableDto(Long userid, Long environmentId, Long envVariableId);

    CreateEnvVariableDto mapCreateEnvVariableDto(CreateEnvVariableModel createEnvVariableModel, Long environmentId);

    ResultModel mapResultModel(CreateEnvVariableDto createEnvVariableDto, List<ErrorMessageDto> errorMessages);

    List<EnvVariableModel> mapEnvVariableModels(List<EnvironmentVariableDao> environmentVariableDaos);

    UpdateEnvVariableDto mapUpdateEnvVariableDto(UpdateEnvVariableModel updateEnvVariableModel, Long environmentId, Long envVariableId);

    EnvVariableModel mapEnvVariableModel(EnvironmentVariableDao environmentVariableDao);

    GetEnvVariableByIdDto mapGetEnvVariableByIdDto(Long environmentId, Long envVariableId);

    EnvVariableModel mapEnvVariableModel(GetEnvVariableByIdDto deleteEnvVariableDto);
}
