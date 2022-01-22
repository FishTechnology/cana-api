package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.GetConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.processors.mappers.ConfigKeyValueServiceProcessorMapper;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.ConfigKeyValueRepository;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ConfigKeyValueServiceProcessorImpl implements ConfigKeyValueServiceProcessor {

    @Inject
    ConfigKeyValueRepository configKeyValueRepository;

    @Inject
    ConfigKeyValueServiceProcessorMapper configKeyValueServiceProcessorMapper;

    @Override
    public List<ErrorMessageDto> processorCreateConfigKeyValue(CreateConfigKeyValueDto createConfigKeyValueDto) {
        return createConfigKeyValue(createConfigKeyValueDto);
    }

    @Override
    public List<ErrorMessageDto> createConfigKeyValue(CreateConfigKeyValueDto createConfigKeyValueDto) {
        var configKeyValueDao = configKeyValueServiceProcessorMapper.mapConfigKeyValueDao(createConfigKeyValueDto);
        configKeyValueRepository.persist(configKeyValueDao);
        createConfigKeyValueDto.setId(configKeyValueDao.getId());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processorGetConfigKeyValue(GetConfigKeyValueDto getConfigKeyValueDto) {
        return getConfigKeyValue(getConfigKeyValueDto);
    }

    @Override
    public List<ErrorMessageDto> getConfigKeyValue(GetConfigKeyValueDto getConfigKeyValueDto) {
        var configKeyValueDaos = configKeyValueRepository.findByConfigId(getConfigKeyValueDto.getConfigId());
        getConfigKeyValueDto.setConfigKeyValueDaos(configKeyValueDaos);
        return Collections.emptyList();
    }

    @Override
    public Long createConfigKeyValue(Long appId, String key, String value, ConfigKeyValueType type, Long userId) {
        var configKeyValueDao = configKeyValueServiceProcessorMapper.mapConfigKeyValueDao(appId, key, value, type, userId);
        configKeyValueRepository.persist(configKeyValueDao);
        return configKeyValueDao.getId();
    }
}
