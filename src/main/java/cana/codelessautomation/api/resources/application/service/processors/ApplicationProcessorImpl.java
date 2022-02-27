package cana.codelessautomation.api.resources.application.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.application.service.dtos.CreateApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.resources.application.service.processors.mappers.ApplicationProcessorMapper;
import cana.codelessautomation.api.resources.application.service.repositories.ApplicationRepository;
import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ApplicationProcessorImpl implements ApplicationProcessor {

    @Inject
    ApplicationProcessorMapper applicationProcessorMapper;

    @Inject
    ApplicationRepository applicationRepository;

    @Override
    public List<ErrorMessageDto> processCreateApplication(CreateApplicationDto createApplicationDto) {
        var errors = createApplication(createApplicationDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return createApplicationConfig(createApplicationDto);
    }

    public List<ErrorMessageDto> createApplicationConfig(CreateApplicationDto createApplicationDto) {
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createApplication(CreateApplicationDto createApplicationDto) {
        ApplicationDao applicationDao = applicationProcessorMapper.mapApplicationDao(createApplicationDto);
        applicationRepository.persist(applicationDao);
        createApplicationDto.setId(applicationDao.getId());
        return Collections.emptyList();
    }

    @Override
    public List<ApplicationDao> processGetApplications(String userId) {
        return applicationRepository.findByUserId(Long.parseLong(userId));
    }

    @Override
    public List<ErrorMessageDto> processDeleteApplication(DeleteApplicationDto deleteApplicationDto) {
        return deleteApplication(deleteApplicationDto);
    }

    @Override
    public List<ErrorMessageDto> deleteApplication(DeleteApplicationDto deleteApplicationDto) {
        ApplicationDao applicationDao = applicationProcessorMapper.mapApplicationDao(deleteApplicationDto);
        applicationRepository.persist(applicationDao);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processUpdateApplication(UpdateApplicationDto updateApplicationDto) {
        return updateApplication(updateApplicationDto);
    }

    @Override
    public List<ErrorMessageDto> updateApplication(UpdateApplicationDto updateApplicationDto) {
        ApplicationDao applicationDao = applicationProcessorMapper.mapApplicationDao(updateApplicationDto);
        applicationRepository.persist(applicationDao);
        return Collections.emptyList();
    }

    @Override
    public ApplicationDao processGetApplicationById(Long applicationId) {
        return applicationRepository.findById(applicationId);
    }
}
