package cana.codelessautomation.api.resources.config.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.config.models.ConfigModel;
import cana.codelessautomation.api.resources.config.models.CreateConfigModel;
import cana.codelessautomation.api.resources.config.service.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.service.dtos.GetConfigsByUserIdDto;
import cana.codelessautomation.api.resources.config.service.repositories.daos.ConfigDao;

import java.util.List;


public interface ConfigResourceMapper {

    GetConfigsByUserIdDto mapGetConfigsByUserIdDto(String userId);

    List<ConfigModel> mapConfigModels(GetConfigsByUserIdDto getConfigsByUserIdDto);

    ConfigModel mapConfigModel(ConfigDao configDao);

    CreateConfigDto mapCreateConfigDto(CreateConfigModel createConfigModel);

    ResultModel mapResultModel(List<ErrorMessageDto> errorMessages, CreateConfigDto createConfigDto);
}
