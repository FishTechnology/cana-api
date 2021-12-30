package cana.codelessautomation.api.resources.config.services.configservice.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByUserIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.processors.mappers.ConfigServiceProcessorMapper;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.ConfigRepository;

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
    public List<ErrorMessageDto> processorGetConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto) {
        return getConfigsByUserId(getConfigsByUserIdDto);
    }

    @Override
    public List<ErrorMessageDto> processorCreateConfig(CreateConfigDto createConfigDto) {
        return createConfig(createConfigDto);
    }

    @Override
    public List<ErrorMessageDto> getConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto) {
        var configDaos =
                configRepository.findByUserId(getConfigsByUserIdDto.getUserId(), getConfigsByUserIdDto.getConfigType());

        if (Objects.isNull(configDaos)) {
            return Collections.emptyList();
        }
        getConfigsByUserIdDto.setConfigDaos(configDaos);
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
    public List<ErrorMessageDto> processorGetConfigById(GetConfigByIdDto getConfigByIdDto) {
        return getConfigById(getConfigByIdDto);
    }

    @Override
    public List<ErrorMessageDto> getConfigById(GetConfigByIdDto getConfigByIdDto) {
        var configDao = configRepository.findByIdAndTypeAndActive(getConfigByIdDto.getConfigType(), getConfigByIdDto.getConfigId());
        getConfigByIdDto.setConfigDao(configDao);
        return Collections.emptyList();
    }
}
