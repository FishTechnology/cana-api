package cana.codelessautomation.api.resources.config.services.configkeyvalueservice;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.GetConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.processors.ConfigKeyValueServiceProcessor;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.verifiers.ConfigKeyValueServiceVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ConfigKeyValueServiceImpl implements ConfigKeyValueService {

    @Inject
    ConfigKeyValueServiceVerifier configKeyValueServiceVerifier;

    @Inject
    ConfigKeyValueServiceProcessor configKeyValueServiceProcessor;

    @Override
    public List<ErrorMessageDto> createConfigKeyValue(CreateConfigKeyValueDto createConfigKeyValueDto) {
        var errorMessages = configKeyValueServiceVerifier.verifyCreateConfigKeyValue(createConfigKeyValueDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return configKeyValueServiceProcessor.processorCreateConfigKeyValue(createConfigKeyValueDto);
    }

    @Override
    public List<ErrorMessageDto> getConfigKeyValue(GetConfigKeyValueDto getConfigKeyValueDto) {
        var errorMessages = configKeyValueServiceVerifier.verifyGetConfigKeyValue(getConfigKeyValueDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return configKeyValueServiceProcessor.processorGetConfigKeyValue(getConfigKeyValueDto);
    }
}
