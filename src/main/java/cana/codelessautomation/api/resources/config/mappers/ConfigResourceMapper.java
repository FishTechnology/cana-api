package cana.codelessautomation.api.resources.config.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.config.models.ConfigModel;
import cana.codelessautomation.api.resources.config.models.CreateConfigModel;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByUserIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;

import java.util.List;


public interface ConfigResourceMapper {

    GetConfigsByUserIdDto mapGetConfigsByUserIdDto(String userId, String configType);

    List<ConfigModel> mapConfigModels(GetConfigsByUserIdDto getConfigsByUserIdDto);

    ConfigModel mapConfigModel(ConfigDao configDao);

    CreateConfigDto mapCreateConfigDto(CreateConfigModel createConfigModel, String configType);

    ResultModel mapResultModel(List<ErrorMessageDto> errorMessages, CreateConfigDto createConfigDto);

    GetConfigByIdDto mapGetConfigByIdDto(String configId, String configType);

    ConfigModel mapConfigModel(GetConfigByIdDto getConfigByIdDto);
}
