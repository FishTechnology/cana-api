package cana.codelessautomation.api.services.applicationconfig;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.applicationconfig.dto.CreateAppConfigDto;
import cana.codelessautomation.api.services.applicationconfig.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.services.applicationconfig.processors.ApplicationConfigProcessor;
import cana.codelessautomation.api.services.applicationconfig.repositories.ApplicationConfigRepository;
import cana.codelessautomation.api.services.applicationconfig.repositories.daos.ApplicationConfigDao;
import cana.codelessautomation.api.services.applicationconfig.verifiers.ApplicationConfigVerifier;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.utilities.CanaUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

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
    public List<ApplicationConfigDao> getApplicationConfigs(String userId) {
        var errorMessages = appConfigVerifier.verifyGetApplicationConfigs(userId);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return appConfigRepository.findByUserId(userId);
    }

    @Override
    public List<ErrorMessageDto> deleteApplicationConfig(UUID applicationConfigId) {
        var errorMessages = appConfigVerifier.verifyDeleteApplicationConfig(applicationConfigId);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return applicationConfigProcessor.processDeleteApplicationConfig(applicationConfigId);
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
