package cana.codelessautomation.api.resources.config.services.configservice.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByAppIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByConfigTypeDto;
import cana.codelessautomation.api.resources.config.services.configservice.processors.mappers.ConfigServiceProcessorMapper;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.ConfigRepository;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigTypeDao;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ConfigServiceProcessorImpl implements ConfigServiceProcessor {
    @Inject
    ConfigRepository configRepository;

    @Inject
    ConfigServiceProcessorMapper configServiceProcessorMapper;

    @Override
    public List<ErrorMessageDto> processorGetConfigsByAppId(GetConfigsByConfigTypeDto getConfigsByConfigTypeDto) {
        var errors = getConfigByType(getConfigsByConfigTypeDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return getConfigsByUserId(getConfigsByConfigTypeDto);
    }

    @Override
    public List<ErrorMessageDto> getConfigByType(GetConfigsByConfigTypeDto getConfigsByConfigTypeDto) {
        var configDaos =
                configRepository.findConfigsByType(getConfigsByConfigTypeDto.getConfigType());
        if (Objects.isNull(configDaos)) {
            return Collections.emptyList();
        }
        getConfigsByConfigTypeDto.setConfigDaos(configDaos);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processorCreateConfig(CreateConfigDto createConfigDto) {
        return createConfig(createConfigDto);
    }

    @Override
    public List<ErrorMessageDto> processorGetConfigsByAppId(GetConfigsByAppIdDto getConfigsByAppIdDto) {
        return getConfigsByAppId(getConfigsByAppIdDto);
    }

    @Override
    public List<ErrorMessageDto> getConfigsByAppId(GetConfigsByAppIdDto getConfigsByAppIdDto) {
        var configDaos =
                configRepository.findByAppId(getConfigsByAppIdDto.getApplicationId());
        if (Objects.isNull(configDaos)) {
            return Collections.emptyList();
        }
        getConfigsByAppIdDto.setConfigDaos(configDaos);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processorGetConfigById(GetConfigByIdDto getConfigByIdDto) {
        return getConfigById(getConfigByIdDto);
    }

    @Override
    public List<ErrorMessageDto> getConfigsByUserId(GetConfigsByConfigTypeDto getConfigsByConfigTypeDto) {

        if (getConfigsByConfigTypeDto.getConfigType() == ConfigTypeDao.SYSTEM_VARIABLE) {
            return Collections.emptyList();
        }
        var configDaos =
                configRepository.findByUserId(getConfigsByConfigTypeDto.getApplicationId(), getConfigsByConfigTypeDto.getConfigType());

        if (Objects.isNull(configDaos)) {
            return Collections.emptyList();
        }
        getConfigsByConfigTypeDto.setConfigDaos(configDaos);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createConfig(CreateConfigDto createConfigDto) {
        var configDao = configServiceProcessorMapper.mapConfigDao(createConfigDto);
        configRepository.persist(configDao);
        createConfigDto.setId(configDao.getId());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> getConfigById(GetConfigByIdDto getConfigByIdDto) {
        var configDao = configRepository.findByIdAndTypeAndActive(getConfigByIdDto.getConfigType(), getConfigByIdDto.getConfigId());
        getConfigByIdDto.setConfigDao(configDao);
        return Collections.emptyList();
    }

    @Override
    public Long createConfig(String name, String type, String value, Long applicationId, Long userId) {
        var configDao = configServiceProcessorMapper.mapConfigDao(name, type, value, applicationId, userId);
        configRepository.persist(configDao);
        return configDao.getId();
    }
}
