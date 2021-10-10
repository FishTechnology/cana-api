package cana.codelessautomation.api.resources.envvariable.mappers;

import cana.codelessautomation.api.resources.commonmodels.PageSetDetailModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.envvariable.models.CreateEnvVariableModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.customer.dtos.EnvPageSetDetailDto;
import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.dtos.DeleteEnvVariableDto;

import java.util.List;

public interface EnvVariableResourceMapper {
    PageSetDetailModel mapPageSetDetailModel(EnvPageSetDetailDto envPageSetDetailDto);

    DeleteEnvVariableDto mapDeleteEnvVariableDto(Long userid, Long envVariableId);

    CreateEnvVariableDto mapCreateEnvVariableDto(CreateEnvVariableModel createEnvVariableModel);

    ResultModel mapResultModel(CreateEnvVariableDto createEnvVariableDto, List<ErrorMessageDto> errorMessages);
}
