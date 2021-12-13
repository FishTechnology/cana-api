package cana.codelessautomation.api.services.application.processors;

import cana.codelessautomation.api.services.application.dtos.CreateApplicationDto;
import cana.codelessautomation.api.services.application.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.services.application.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.services.application.processors.mappers.ApplicationProcessorMapper;
import cana.codelessautomation.api.services.application.repositories.ApplicationRepository;
import cana.codelessautomation.api.services.application.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;

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
        return createApplication(createApplicationDto);
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
