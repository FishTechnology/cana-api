package cana.codelessautomation.api.resources.config.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.config.models.ConfigKeyValueModel;
import cana.codelessautomation.api.resources.config.models.CreateConfigKeyValueModel;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.GetConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueDao;

import java.util.List;

public interface ConfigKeyValueResourceMapper {
    CreateConfigKeyValueDto mapCreateConfigKeyValueDto(String configType, String configId, CreateConfigKeyValueModel createConfigKeyValue);

    ResultModel mapResultModel(List<ErrorMessageDto> errorMessages, CreateConfigKeyValueDto createConfigKeyValueDto);

    GetConfigKeyValueDto mapGetConfigKeyValueDto(Long configId);

    List<ConfigKeyValueModel> mapConfigKeyValueModel(GetConfigKeyValueDto getConfigKeyValueDto);

    ConfigKeyValueModel mapConfigKeyValueModel(ConfigKeyValueDao configKeyValueDao);
}
