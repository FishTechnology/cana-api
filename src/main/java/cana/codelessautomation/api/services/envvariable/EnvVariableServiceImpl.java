package cana.codelessautomation.api.services.envvariable;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.customer.dtos.EnvPageSetDetailDto;
import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.processors.EnvVariableServiceProcessor;
import cana.codelessautomation.api.services.envvariable.verifiers.EnvVariableVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class EnvVariableServiceImpl implements EnvVariableService {

    @Inject
    EnvVariableVerifier envVariableVerifier;

    @Inject
    EnvVariableServiceProcessor envVariableServiceProcessor;

    @Override
    public EnvPageSetDetailDto getEnvVariables(long userId, int pageNumber, int pageSize) throws ValidationException {
        var errorMessages = envVariableVerifier.verifyGetEnvVariables(userId);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }

        return envVariableServiceProcessor.processorGetEnvVariables(userId, pageNumber, pageSize);
    }

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
}
