package cana.codelessautomation.api.resources.environment.service;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.environment.service.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.UpdateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.processors.EnvironmentProcessor;
import cana.codelessautomation.api.resources.environment.service.repositories.EnvironmentRepository;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.resources.environment.service.verifiers.EnvironmentVerifier;
import cana.codelessautomation.api.commons.utilities.CanaUtility;

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

    @Inject
    EnvironmentRepository environmentRepository;

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

    @Override
    public EnvironmentDao getEnvironment(Long environmentId) {
        return environmentRepository.findByIdAndActive(environmentId);
    }

    @Override
    public List<ErrorMessageDto> updateEnvironment(UpdateEnvironmentDto updateEnvironment) throws ValidationException {
        updateEnvironment.setCreatedOn(OffsetDateTime.now());
        updateEnvironment.setModifiedOn(OffsetDateTime.now());
        updateEnvironment.setCreatedBy(updateEnvironment.getUserId().toString());
        updateEnvironment.setModifiedBy(updateEnvironment.getUserId().toString());
        updateEnvironment.setIsActive(true);

        var errorMessages = environmentVerifier.verifyUpdateEnvironment(updateEnvironment);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return environmentProcessor.processUpdateEnvironment(updateEnvironment);
    }
}
