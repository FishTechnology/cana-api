package cana.codelessautomation.api.services.envvariable;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.dtos.GetEnvVariableByIdDto;
import cana.codelessautomation.api.services.envvariable.dtos.UpdateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.processors.EnvVariableServiceProcessor;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;
import cana.codelessautomation.api.services.envvariable.verifiers.EnvVariableVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;

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
