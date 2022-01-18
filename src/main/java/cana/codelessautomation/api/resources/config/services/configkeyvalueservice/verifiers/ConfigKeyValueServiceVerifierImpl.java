package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.application.service.verifiers.ApplicationVerifier;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.GetConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.errorcodes.ConfigKeyValueServiceErrorCode;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.ConfigKeyValueRepository;
import cana.codelessautomation.api.resources.config.services.configservice.verifiers.ConfigServiceVerifier;
import cana.codelessautomation.api.resources.customer.service.verifiers.CustomerServiceVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ConfigKeyValueServiceVerifierImpl implements ConfigKeyValueServiceVerifier {

    @Inject
    ConfigKeyValueRepository configKeyValueRepository;

    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Inject
    ConfigServiceVerifier configServiceVerifier;

    @Inject
    ConfigKeyValueServiceErrorCode configKeyValueServiceErrorCode;

    @Inject
    ApplicationVerifier applicationVerifier;

    @Override
    public List<ErrorMessageDto> verifyCreateConfigKeyValue(CreateConfigKeyValueDto createConfigKeyValueDto) {
        var errors = isUserIdValid(createConfigKeyValueDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }

        errors = isConfigIdValid(createConfigKeyValueDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isApplicationIdValid(createConfigKeyValueDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isConfigKeyValid(createConfigKeyValueDto);
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(CreateConfigKeyValueDto createConfigKeyValueDto) {
        var response = applicationVerifier.isApplicationIdValid(createConfigKeyValueDto.getApplicationId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createConfigKeyValueDto.setApplication(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isConfigKeyValid(CreateConfigKeyValueDto createConfigKeyValueDto) {
        var configKeyValueDao = configKeyValueRepository.findByConfigIdAndKey(createConfigKeyValueDto.getConfigId(), createConfigKeyValueDto.getKey());
        if (Objects.isNull(configKeyValueDao)) {
            return Collections.emptyList();
        }

        return CanaUtility.getErrorMessages(configKeyValueServiceErrorCode.getConfigKeyValueDuplicateErrorCode());
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(CreateConfigKeyValueDto createConfigKeyValueDto) {
        var response = customerServiceVerifier.isUserIdValid(createConfigKeyValueDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createConfigKeyValueDto.setCustomDetailDao(response.getValue());
        return Collections.emptyList();
    }


    @Override
    public List<ErrorMessageDto> isConfigIdValid(CreateConfigKeyValueDto createConfigKeyValueDto) {
        var response = configServiceVerifier.isConfigIdValid(createConfigKeyValueDto.getConfigId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createConfigKeyValueDto.setConfigDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyGetConfigKeyValue(GetConfigKeyValueDto getConfigKeyValueDto) {
        return isConfigKeyValid(getConfigKeyValueDto);
    }

    @Override
    public List<ErrorMessageDto> isConfigKeyValid(GetConfigKeyValueDto getConfigKeyValueDto) {
        var response = configServiceVerifier.isConfigIdValid(getConfigKeyValueDto.getConfigId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        getConfigKeyValueDto.setConfigDao(response.getValue());
        return Collections.emptyList();
    }
}
