package cana.codelessautomation.api.services.applicationconfig.processors;

import cana.codelessautomation.api.services.applicationconfig.dto.CreateAppConfigDto;
import cana.codelessautomation.api.services.applicationconfig.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.services.applicationconfig.processors.mappers.ApplicationConfigProcessorMapper;
import cana.codelessautomation.api.services.applicationconfig.repositories.ApplicationConfigRepository;
import cana.codelessautomation.api.services.applicationconfig.repositories.daos.ApplicationConfigDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ApplicationConfigProcessorImpl implements ApplicationConfigProcessor {
    @Inject
    ApplicationConfigProcessorMapper appConfigProcessorMapper;

    @Inject
    ApplicationConfigRepository appConfigRepository;

    @Override
    public List<ErrorMessageDto> processCreateApplicationConfig(CreateAppConfigDto createAppConfigDto) {
        ApplicationConfigDao applicationConfigDao = appConfigProcessorMapper.mapApplicationConfigDao(createAppConfigDto);
        appConfigRepository.persist(applicationConfigDao);
        createAppConfigDto.setId(applicationConfigDao.getId());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processDeleteApplicationConfig(UUID applicationConfigId) {
        return deleteApplicationConfig(applicationConfigId);

    }

    @Override
    public List<ErrorMessageDto> deleteApplicationConfig(UUID applicationConfigId) {
        appConfigRepository.deleteAppConfig(applicationConfigId);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processUpdateApplicationConfig(UpdateApplicationConfigDto updateApplicationConfigDto) {
        return updateApplicationConfig(updateApplicationConfigDto);
    }

    @Override
    public List<ErrorMessageDto> updateApplicationConfig(UpdateApplicationConfigDto updateApplicationConfigDto) {
        ApplicationConfigDao applicationConfigDao = appConfigProcessorMapper.mapApplicationConfigDao(updateApplicationConfigDto);
        appConfigRepository.persist(applicationConfigDao);
        return Collections.emptyList();
    }
}
