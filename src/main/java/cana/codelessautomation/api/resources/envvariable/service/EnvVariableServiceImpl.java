package cana.codelessautomation.api.resources.envvariable.service;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.GetEnvVariableByIdDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.UpdateEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.processors.EnvVariableServiceProcessor;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableDao;
import cana.codelessautomation.api.resources.envvariable.service.verifiers.EnvVariableVerifier;
import cana.codelessautomation.api.commons.utilities.CanaUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class EnvVariableServiceImpl implements EnvVariableService {

    @Inject
    EnvVariableVerifier envVariableVerifier;

    @Inject
    EnvVariableServiceProcessor envVariableServiceProcessor;

    @Override
    public List<ErrorMessageDto> deleteEnvVariable(DeleteEnvVariableDto deleteEnvVariableDto) throws ValidationException {
        var errorMessages = envVariableVerifier.verifyDeleteEnvVariable(deleteEnvVariableDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }

        return envVariableServiceProcessor.processorDeleteEnvVariable(deleteEnvVariableDto);
    }

    @Override
    public List<ErrorMessageDto> createEnvVariable(CreateEnvVariableDto createEnvVariableDto) throws ValidationException {
        createEnvVariableDto.setCreatedOn(OffsetDateTime.now());
        createEnvVariableDto.setModifiedOn(OffsetDateTime.now());
        createEnvVariableDto.setCreatedBy(createEnvVariableDto.getUserId().toString());
        createEnvVariableDto.setModifiedBy(createEnvVariableDto.getUserId().toString());
        createEnvVariableDto.setIsActive(true);

        var errorMessages = envVariableVerifier.verifyCreateEnvVariable(createEnvVariableDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }

        return envVariableServiceProcessor.processorCreateEnvVariable(createEnvVariableDto);
    }

    @Override
    public List<EnvironmentVariableDao> getEnvVariables(long environmentId) {
        var errorMessages = envVariableVerifier.verifyGetEnvVariables(environmentId);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }

        return envVariableServiceProcessor.processorGetEnvVariables(environmentId);
    }

    @Override
    public List<ErrorMessageDto> updateEnvVariable(UpdateEnvVariableDto updateEnvVariableDto) {
        return null;
    }

    @Override
    public List<ErrorMessageDto> getEnvVariableById(GetEnvVariableByIdDto getEnvVariableByIdDto) {
        var errorMessages = envVariableVerifier.verifyGetEnvVariableById(getEnvVariableByIdDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return Collections.emptyList();
    }
}
