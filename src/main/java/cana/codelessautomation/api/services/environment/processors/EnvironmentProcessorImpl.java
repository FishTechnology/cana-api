package cana.codelessautomation.api.services.environment.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.processors.mappers.EnvironmentProcessorMapper;
import cana.codelessautomation.api.services.environment.repositories.EnvironmentRepository;

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
    public List<ErrorMessageDto> createEnvironment(CreateEnvironmentDto createEnvironment) {
        var environmentDao = environmentProcessorMapper.mapEnvironmentDao(createEnvironment);
        environmentRepository.persist(environmentDao);
        createEnvironment.setId(environmentDao.getId());
        return Collections.emptyList();
    }
}
