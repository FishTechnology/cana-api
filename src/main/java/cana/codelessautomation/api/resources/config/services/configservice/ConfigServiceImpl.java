package cana.codelessautomation.api.resources.config.services.configservice;


import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByUserIdDto;
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
    public List<ErrorMessageDto> getConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto) {
        var errorMessages = configServiceVerifier.verifyGetConfigsByUserId(getConfigsByUserIdDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return configServiceProcessor.processorGetConfigsByUserId(getConfigsByUserIdDto);
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
        return configServiceProcessor.processorGetConfigById(getConfigByIdDto);
    }
}
