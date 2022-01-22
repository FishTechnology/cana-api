package cana.codelessautomation.api.resources.config.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.config.models.ConfigModel;
import cana.codelessautomation.api.resources.config.models.CreateConfigModel;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByAppIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByConfigTypeDto;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;

import java.util.List;


public interface ConfigResourceMapper {

    GetConfigsByConfigTypeDto mapGetConfigsByConfigTypeDto(Long applicationId, String configType);

    List<ConfigModel> mapConfigModels(GetConfigsByConfigTypeDto getConfigsByConfigTypeDto);

    ConfigModel mapConfigModel(ConfigDao configDao);

    CreateConfigDto mapCreateConfigDto(Long applicationId, CreateConfigModel createConfigModel, String configType);

    ResultModel mapResultModel(List<ErrorMessageDto> errorMessages, CreateConfigDto createConfigDto);

    GetConfigByIdDto mapGetConfigByIdDto(Long applicationId, String configId, String configType);

    ConfigModel mapConfigModel(GetConfigByIdDto getConfigByIdDto);

    GetConfigsByAppIdDto mapGetConfigsByAppIdDto(Long applicationId);

    List<ConfigModel> mapConfigModels(GetConfigsByAppIdDto getConfigsByAppIdDto);
}
