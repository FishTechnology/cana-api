package cana.codelessautomation.api.resources.config.services.configservice;


import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByAppIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.processors.ConfigServiceProcessor;
import cana.codelessautomation.api.resources.config.services.configservice.verifiers.ConfigServiceVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ConfigServiceImpl implements ConfigService {
    @Inject
    ConfigServiceVerifier configServiceVerifier;

    @Inject
    ConfigServiceProcessor configServiceProcessor;

    @Override
    public List<ErrorMessageDto> getConfigsByAppId(GetConfigsByAppIdDto getConfigsByAppIdDto) {
        var errorMessages = configServiceVerifier.verifyGetConfigsByUserId(getConfigsByAppIdDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return configServiceProcessor.processorGetConfigsByAppId(getConfigsByAppIdDto);
    }

    @Override
    public List<ErrorMessageDto> createConfig(CreateConfigDto createConfigDto) {
        var errorMessages = configServiceVerifier.verifyCreateConfig(createConfigDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return configServiceProcessor.processorCreateConfig(createConfigDto);
    }

    @Override
    public List<ErrorMessageDto> getConfigById(GetConfigByIdDto getConfigByIdDto) {
        var errorMessages = configServiceVerifier.verifyGetConfigById(getConfigByIdDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return configServiceProcessor.processorGetConfigById(getConfigByIdDto);
    }
}
