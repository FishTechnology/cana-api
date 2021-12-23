package cana.codelessautomation.api.resources.config.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.config.service.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.service.dtos.GetConfigsByUserIdDto;
import cana.codelessautomation.api.resources.config.service.processors.mappers.ConfigServiceProcessorMapper;
import cana.codelessautomation.api.resources.config.service.repositories.ConfigRepository;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

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
    public List<ErrorMessageDto> getConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto) {
        var configDaos =
                configRepository.findByUserId(getConfigsByUserIdDto.getUserId());

        if (CollectionUtils.isEmpty(configDaos)) {
            return Collections.emptyList();
        }
        getConfigsByUserIdDto.setConfigDaos(configDaos);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processorCreateConfig(CreateConfigDto createConfigDto) {
        return createConfig(createConfigDto);
    }

    @Override
    public List<ErrorMessageDto> createConfig(CreateConfigDto createConfigDto) {
        var configDao = configServiceProcessorMapper.mapConfigDao(createConfigDto);
        configRepository.persist(configDao);
        createConfigDto.setId(configDao.getId());
        return Collections.emptyList();
    }
}
