package cana.codelessautomation.api.services.environment.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.services.environment.processors.mappers.EnvironmentProcessorMapper;
import cana.codelessautomation.api.services.environment.repositories.EnvironmentRepository;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;

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
