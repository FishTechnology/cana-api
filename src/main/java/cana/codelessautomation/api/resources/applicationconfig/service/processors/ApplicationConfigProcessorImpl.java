package cana.codelessautomation.api.resources.applicationconfig.service.processors;

import cana.codelessautomation.api.resources.applicationconfig.service.dto.CreateAppConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.DeleteApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.GetApplicationConfigsDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.processors.mappers.ApplicationConfigProcessorMapper;
import cana.codelessautomation.api.resources.applicationconfig.service.repositories.ApplicationConfigRepository;
import cana.codelessautomation.api.resources.applicationconfig.service.repositories.daos.ApplicationConfigDao;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

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
    public List<ErrorMessageDto> processDeleteApplicationConfig(DeleteApplicationConfigDto deleteApplicationConfigDto) {
        return deleteApplicationConfig(deleteApplicationConfigDto);

    }

    public List<ErrorMessageDto> deleteApplicationConfig(DeleteApplicationConfigDto deleteApplicationConfigDto) {
        appConfigRepository.deleteAppConfig(deleteApplicationConfigDto.getApplicationId(), deleteApplicationConfigDto.getApplicationConfigId());
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

    @Override
    public List<ApplicationConfigDao> processGetApplicationConfigs(GetApplicationConfigsDto getApplicationConfigsDto) {
        return getApplicationConfigs(getApplicationConfigsDto);
    }

    @Override
    public List<ApplicationConfigDao> getApplicationConfigs(GetApplicationConfigsDto getApplicationConfigsDto) {
        return appConfigRepository.findByAppId(getApplicationConfigsDto.getApplicationId());
    }
}
