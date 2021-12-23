package cana.codelessautomation.api.resources.applicationconfig.service;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.CreateAppConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.DeleteApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.GetApplicationConfigsDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.processors.ApplicationConfigProcessor;
import cana.codelessautomation.api.resources.applicationconfig.service.repositories.ApplicationConfigRepository;
import cana.codelessautomation.api.resources.applicationconfig.service.repositories.daos.ApplicationConfigDao;
import cana.codelessautomation.api.resources.applicationconfig.service.verifiers.ApplicationConfigVerifier;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ApplicationConfigServiceImpl implements ApplicationConfigService {

    @Inject
    ApplicationConfigVerifier appConfigVerifier;

    @Inject
    ApplicationConfigProcessor applicationConfigProcessor;

    @Inject
    ApplicationConfigRepository appConfigRepository;


    @Override
    public List<ErrorMessageDto> createApplicationConfig(CreateAppConfigDto createAppConfigDto) {
        createAppConfigDto.setCreatedBy(createAppConfigDto.getUserId());
        createAppConfigDto.setModifiedBy(createAppConfigDto.getUserId());
        createAppConfigDto.setIsActive(true);

        var errorMessages = appConfigVerifier.verifyCreateApplicationConfig(createAppConfigDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return applicationConfigProcessor.processCreateApplicationConfig(createAppConfigDto);
    }

    @Override
    public List<ApplicationConfigDao> getApplicationConfigs(GetApplicationConfigsDto getApplicationConfigsDto) {
        var errorMessages = appConfigVerifier.verifyGetApplicationConfigs(getApplicationConfigsDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return applicationConfigProcessor.processGetApplicationConfigs(getApplicationConfigsDto);
    }

    @Override
    public List<ErrorMessageDto> deleteApplicationConfig(DeleteApplicationConfigDto deleteApplicationConfigDto) {
        var errorMessages = appConfigVerifier.verifyDeleteApplicationConfig(deleteApplicationConfigDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return applicationConfigProcessor.processDeleteApplicationConfig(deleteApplicationConfigDto);
    }

    @Override
    public List<ErrorMessageDto> updateApplicationConfig(UpdateApplicationConfigDto updateApplicationConfigDto) {
        var errorMessages = appConfigVerifier.verifyUpdateApplicationConfig(updateApplicationConfigDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return applicationConfigProcessor.processUpdateApplicationConfig(updateApplicationConfigDto);
    }
}
