package cana.codelessautomation.api.resources.config.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.config.models.ConfigKeyValueModel;
import cana.codelessautomation.api.resources.config.models.CreateConfigKeyValueModel;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.GetConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueDao;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ConfigKeyValueResourceMapperImpl implements ConfigKeyValueResourceMapper {
    @Override
    public CreateConfigKeyValueDto mapCreateConfigKeyValueDto(Long applicationId, String configType, String configId, CreateConfigKeyValueModel createConfigKeyValue) {
        CreateConfigKeyValueDto createConfigKeyValueDto = new CreateConfigKeyValueDto();
        createConfigKeyValueDto.setKey(createConfigKeyValue.getKey());
        createConfigKeyValueDto.setValue(createConfigKeyValue.getValue());
        createConfigKeyValueDto.setFileId(createConfigKeyValue.getFileId());
        createConfigKeyValueDto.setComments(createConfigKeyValue.getComments());
        createConfigKeyValueDto.setUserId(createConfigKeyValue.getUserId());
        createConfigKeyValueDto.setApplicationId(applicationId);
        createConfigKeyValueDto.setType(EnumUtils.getEnumIgnoreCase(ConfigKeyValueType.class, createConfigKeyValue.getType()));
        createConfigKeyValueDto.setConfigId(Long.valueOf(configId));
        return createConfigKeyValueDto;
    }

    @Override
    public ResultModel mapResultModel(List<ErrorMessageDto> errorMessages, CreateConfigKeyValueDto createConfigKeyValueDto) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createConfigKeyValueDto.getId().toString());
        return resultModel;
    }

    @Override
    public GetConfigKeyValueDto mapGetConfigKeyValueDto(Long configId) {
        GetConfigKeyValueDto getConfigKeyValueDto = new GetConfigKeyValueDto();
        getConfigKeyValueDto.setConfigId(configId);
        return getConfigKeyValueDto;
    }

    @Override
    public List<ConfigKeyValueModel> mapConfigKeyValueModel(GetConfigKeyValueDto getConfigKeyValueDto) {
        List<ConfigKeyValueModel> configKeyValueModels = new ArrayList<>();
        for (ConfigKeyValueDao configKeyValueDao : getConfigKeyValueDto.getConfigKeyValueDaos()) {
            configKeyValueModels.add(mapConfigKeyValueModel(configKeyValueDao));
        }
        return configKeyValueModels;
    }

    @Override
    public ConfigKeyValueModel mapConfigKeyValueModel(ConfigKeyValueDao configKeyValueDao) {
        ConfigKeyValueModel configKeyValueModel = new ConfigKeyValueModel();
        configKeyValueModel.setId(configKeyValueDao.getId().toString());
        configKeyValueModel.setConfigId(configKeyValueDao.getConfigId().toString());
        configKeyValueModel.setKey(configKeyValueDao.getKey());
        configKeyValueModel.setValue(configKeyValueDao.getValue());
        configKeyValueModel.setCreatedBy(configKeyValueDao.getCreatedBy());
        configKeyValueModel.setModifiedBy(configKeyValueDao.getModifiedBy());
        configKeyValueModel.setCreatedOn(configKeyValueDao.getCreatedOn().toString());
        configKeyValueModel.setModifiedOn(configKeyValueDao.getModifiedOn().toString());
        configKeyValueModel.setUserId(configKeyValueDao.getUserId().toString());
        configKeyValueModel.setComments(configKeyValueDao.getComments());
        configKeyValueModel.setIsActive(configKeyValueDao.getIsActive());
        configKeyValueModel.setType(configKeyValueDao.getType().name());
        return configKeyValueModel;
    }
}
