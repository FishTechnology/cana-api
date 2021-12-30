package cana.codelessautomation.api.resources.config.mappers;


import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.config.models.ConfigKeyValueModel;
import cana.codelessautomation.api.resources.config.models.ConfigModel;
import cana.codelessautomation.api.resources.config.models.CreateConfigModel;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueDao;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByUserIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigTypeDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ConfigResourceMapperImpl implements ConfigResourceMapper {

    @Inject
    ConfigKeyValueResourceMapper configKeyValueResourceMapper;

    @Override
    public GetConfigsByUserIdDto mapGetConfigsByUserIdDto(String userId, String configType) {
        GetConfigsByUserIdDto getTestCaseByTestPlanId = new GetConfigsByUserIdDto();
        getTestCaseByTestPlanId.setUserId(Long.valueOf(userId));
        getTestCaseByTestPlanId.setConfigType(EnumUtils.getEnum(ConfigTypeDao.class, configType));
        return getTestCaseByTestPlanId;
    }

    @Override
    public List<ConfigModel> mapConfigModels(GetConfigsByUserIdDto getConfigsByUserIdDto) {
        List<ConfigModel> configModels = new ArrayList<>();
        for (ConfigDao configDao : getConfigsByUserIdDto.getConfigDaos()) {
            configModels.add(mapConfigModel(configDao));
        }
        return configModels;
    }

    @Override
    public ConfigModel mapConfigModel(ConfigDao configDao) {
        ConfigModel configModel = new ConfigModel();
        configModel.setId(configDao.getId().toString());
        configModel.setCreatedBy(configDao.getCreatedBy());
        configModel.setModifiedBy(configDao.getModifiedBy());
        configModel.setCreatedOn(configDao.getCreatedOn().toString());
        configModel.setModifiedOn(configDao.getModifiedOn().toString());
        configModel.setType(configDao.getType().name());
        configModel.setName(configDao.getName());
        configModel.setComments(configDao.getComments());
        configModel.setUserId(configDao.getUserId().toString());
        configModel.setIsActive(configDao.getIsActive());
        if (CollectionUtils.isEmpty(configDao.getConfigKeyValues())) {
            return configModel;
        }

        var configKeyValueModels = new ArrayList<ConfigKeyValueModel>();

        for (ConfigKeyValueDao configKeyValueDao : configDao.getConfigKeyValues()) {
            configKeyValueModels.add(configKeyValueResourceMapper.mapConfigKeyValueModel(configKeyValueDao));
        }
        configModel.setConfigKeyValues(configKeyValueModels);

        return configModel;
    }

    @Override
    public CreateConfigDto mapCreateConfigDto(CreateConfigModel createConfigModel) {
        CreateConfigDto createConfigDto = new CreateConfigDto();
        createConfigDto.setUserId(createConfigModel.getUserId());
        createConfigDto.setName(createConfigModel.getName());
        createConfigDto.setComments(createConfigModel.getComments());
        createConfigDto.setType(EnumUtils.getEnum(ConfigTypeDao.class, createConfigModel.getType()));
        return createConfigDto;
    }

    @Override
    public ResultModel mapResultModel(List<ErrorMessageDto> errorMessages, CreateConfigDto createConfigDto) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createConfigDto.getId().toString());
        return resultModel;
    }

    @Override
    public GetConfigByIdDto mapGetConfigByIdDto(String configId, String configType) {
        GetConfigByIdDto getConfigByIdDto = new GetConfigByIdDto();
        getConfigByIdDto.setConfigId(Long.valueOf(configId));
        getConfigByIdDto.setConfigType(EnumUtils.getEnumIgnoreCase(ConfigTypeDao.class, configType));
        return getConfigByIdDto;
    }

    @Override
    public ConfigModel mapConfigModel(GetConfigByIdDto getConfigByIdDto) {
        return mapConfigModel(getConfigByIdDto.getConfigDao());
    }
}


