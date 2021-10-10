package cana.codelessautomation.api.services.environment;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.services.environment.processors.EnvironmentProcessor;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.services.environment.verifiers.EnvironmentVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class EnvironmentServiceImpl implements EnvironmentService {

    @Inject
    EnvironmentVerifier environmentVerifier;

    @Inject
    EnvironmentProcessor environmentProcessor;

    @Override
    public List<ErrorMessageDto> createEnvironment(CreateEnvironmentDto createEnvironment) throws ValidationException {
        createEnvironment.setCreatedOn(OffsetDateTime.now());
        createEnvironment.setModifiedOn(OffsetDateTime.now());
        createEnvironment.setCreatedBy(createEnvironment.getUserId().toString());
        createEnvironment.setModifiedBy(createEnvironment.getUserId().toString());
        createEnvironment.setIsActive(true);

        var errorMessages = environmentVerifier.verifyCreateEnvironment(createEnvironment);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return environmentProcessor.processCreateEnvironment(createEnvironment);
    }

    @Override
    public List<EnvironmentDao> getEnvironments(Long userId) throws ValidationException {
        var errorMessages = environmentVerifier.verifyGetEnvironments(userId);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return environmentProcessor.processGetEnvironments(userId);
    }

    @Override
    public List<ErrorMessageDto> deleteEnvironment(DeleteEnvironmentDto deleteEnvironment) throws ValidationException {
        var errorMessages = environmentVerifier.verifyDeleteEnvironment(deleteEnvironment);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return environmentProcessor.processDeleteEnvironment(deleteEnvironment);
    }
}
