package cana.codelessautomation.api.services.application;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.application.dtos.CreateApplicationDto;
import cana.codelessautomation.api.services.application.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.services.application.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.services.application.processors.ApplicationProcessor;
import cana.codelessautomation.api.services.application.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.services.application.verifiers.ApplicationVerifier;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.utilities.CanaUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ApplicationServiceImpl implements ApplicationService {
    @Inject
    ApplicationProcessor applicationProcessor;

    @Inject
    ApplicationVerifier applicationVerifier;

    @Override
    public List<ErrorMessageDto> createApplication(CreateApplicationDto createApplicationDto) {
        createApplicationDto.setCreatedBy(createApplicationDto.getUserId().toString());
        createApplicationDto.setModifiedBy(createApplicationDto.getUserId().toString());
        createApplicationDto.setIsActive(true);

        var errorMessages = applicationVerifier.verifyCreateApplication(createApplicationDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return applicationProcessor.processCreateApplication(createApplicationDto);
    }

    @Override
    public List<ApplicationDao> getApplications(String userId) {
        return applicationProcessor.processGetApplications(userId);
    }

    @Override
    public List<ErrorMessageDto> deleteApplication(DeleteApplicationDto deleteApplicationDto) {
        var errorMessages = applicationVerifier.verifyDeleteApplication(deleteApplicationDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return applicationProcessor.processDeleteApplication(deleteApplicationDto);
    }

    @Override
    public List<ErrorMessageDto> updateApplication(UpdateApplicationDto updateApplicationDto) {
        updateApplicationDto.setModifiedBy(updateApplicationDto.getUserId().toString());

        var errorMessages = applicationVerifier.verifyUpdateApplication(updateApplicationDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return applicationProcessor.processUpdateApplication(updateApplicationDto);
    }

    @Override
    public ApplicationDao getApplicationById(Long applicationId) {
        return applicationProcessor.processGetApplicationById(applicationId);
    }
}
