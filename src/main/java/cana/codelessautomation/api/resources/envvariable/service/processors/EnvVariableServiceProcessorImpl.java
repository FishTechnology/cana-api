package cana.codelessautomation.api.resources.envvariable.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.customer.service.dtos.EnvPageSetDetailDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.processors.mappers.EnvVariableServiceProcessorMapper;
import cana.codelessautomation.api.resources.envvariable.service.repositories.EnvVariableRepository;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class EnvVariableServiceProcessorImpl implements EnvVariableServiceProcessor {

    @Inject
    EnvVariableRepository envVariableRepository;

    @Inject
    EnvVariableServiceProcessorMapper envVariableServiceProcessorMapper;

    @Override
    public EnvPageSetDetailDto processorGetEnvVariables(long userId, int pageNumber, int pageSize) {
        return envVariableRepository.GetEnvPageSet(userId, pageNumber, pageSize);
    }

    @Override
    public List<ErrorMessageDto> processorDeleteEnvVariable(DeleteEnvVariableDto deleteEnvVariableDto) {
        envVariableRepository.deleteByEnvId(deleteEnvVariableDto.getUserid(),
                deleteEnvVariableDto.getEnvironmentId(),
                deleteEnvVariableDto.getEnvVariableId());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processorCreateEnvVariable(CreateEnvVariableDto createEnvVariableDto) {
        return createEnvVariable(createEnvVariableDto);
    }

    @Override
    public List<ErrorMessageDto> createEnvVariable(CreateEnvVariableDto createEnvVariableDto) {
        var environmentVariable = envVariableServiceProcessorMapper.mapEnvironmentVariableDao(createEnvVariableDto);
        envVariableRepository.persist(environmentVariable);
        createEnvVariableDto.setId(environmentVariable.getId());
        return Collections.emptyList();
    }

    @Override
    public List<EnvironmentVariableDao> processorGetEnvVariables(long environmentId) {
        return getEnvVariables(environmentId);
    }

    @Override
    public List<EnvironmentVariableDao> getEnvVariables(long environmentId) {
        return envVariableRepository.findByEnvId(environmentId);
    }
}
