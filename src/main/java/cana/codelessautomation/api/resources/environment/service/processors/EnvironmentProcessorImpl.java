package cana.codelessautomation.api.resources.environment.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.environment.service.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.UpdateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.processors.mappers.EnvironmentProcessorMapper;
import cana.codelessautomation.api.resources.environment.service.repositories.EnvironmentRepository;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class EnvironmentProcessorImpl implements EnvironmentProcessor {

    @Inject
    EnvironmentProcessorMapper environmentProcessorMapper;

    @Inject
    EnvironmentRepository environmentRepository;

    @Override
    public List<ErrorMessageDto> processCreateEnvironment(CreateEnvironmentDto createEnvironment) {
        return createEnvironment(createEnvironment);
    }

    @Override
    public List<ErrorMessageDto> processDeleteEnvironment(DeleteEnvironmentDto deleteEnvironment) {
        return deleteEnvironment(deleteEnvironment);
    }

    @Override
    public List<ErrorMessageDto> processUpdateEnvironment(UpdateEnvironmentDto updateEnvironment) {
        return updateEnvironment(updateEnvironment);
    }

    @Override
    public List<ErrorMessageDto> updateEnvironment(UpdateEnvironmentDto updateEnvironment) {
        environmentRepository.updateEnv(updateEnvironment);
        return Collections.emptyList();
    }

    @Override
    public List<EnvironmentDao> processGetEnvironments(Long userId) {
        return getEnvironments(userId);
    }

    @Override
    public List<ErrorMessageDto> deleteEnvironment(DeleteEnvironmentDto deleteEnvironment) {
        environmentRepository.deleteEnvironment(deleteEnvironment.getEnvironmentId());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createEnvironment(CreateEnvironmentDto createEnvironment) {
        var environmentDao = environmentProcessorMapper.mapEnvironmentDao(createEnvironment);
        environmentRepository.persist(environmentDao);
        createEnvironment.setId(environmentDao.getId());
        return Collections.emptyList();
    }

    @Override
    public List<EnvironmentDao> getEnvironments(Long userId) {
        return environmentRepository.findByUserId(userId);
    }
}
