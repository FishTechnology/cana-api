package cana.codelessautomation.api.resources.config.services.configservice.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.application.service.verifiers.ApplicationVerifier;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigByIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByAppIdDto;
import cana.codelessautomation.api.resources.config.services.configservice.errorcodes.ConfigServiceErrorCode;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.ConfigRepository;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import cana.codelessautomation.api.resources.customer.service.verifiers.CustomerServiceVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ConfigServiceVerifierImpl implements ConfigServiceVerifier {
    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Inject
    ConfigRepository configRepository;

    @Inject
    ConfigServiceErrorCode configServiceErrorCode;

    @Inject
    ApplicationVerifier applicationVerifier;

    @Override
    public List<ErrorMessageDto> verifyGetConfigsByUserId(GetConfigsByAppIdDto getConfigsByAppIdDto) {
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyCreateConfig(CreateConfigDto createConfigDto) {
        var errors = isUserIdValid(createConfigDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isApplicationIdValid(createConfigDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return checkConfigTypeDuplicate(createConfigDto);
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(CreateConfigDto createConfigDto) {
        var response = applicationVerifier.isApplicationIdValid(createConfigDto.getApplicationId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createConfigDto.setApplication(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyGetConfigById(GetConfigByIdDto getConfigByIdDto) {
        return isApplicationIdValid(getConfigByIdDto);
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(GetConfigByIdDto getConfigByIdDto) {
        var response = applicationVerifier.isApplicationIdValid(getConfigByIdDto.getApplicationId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        getConfigByIdDto.setApplication(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> checkConfigTypeDuplicate(CreateConfigDto createConfigDto) {
        if (Objects.isNull(createConfigDto.getIdentifier()) || createConfigDto.getIdentifier() > 0) {
            return Collections.emptyList();
        }
        var configDaos = configRepository.findByUserIdAndTypeAndName(createConfigDto.getUserId(), createConfigDto.getType(), createConfigDto.getName());
        if (CollectionUtils.isNotEmpty(configDaos)) {
            return CanaUtility.getErrorMessages(configServiceErrorCode.getConfigTypeDuplicateErrorCode());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> checkConfigTypeWithIdentifierDuplicate(CreateConfigDto createConfigDto) {
        if (Objects.isNull(createConfigDto.getIdentifier()) || createConfigDto.getIdentifier() == 0) {
            return Collections.emptyList();
        }
        var configDaos = configRepository.findByUserIdAndTypeAndNameAndIdentifier(createConfigDto.getUserId(), createConfigDto.getType(), createConfigDto.getName(), createConfigDto.getIdentifier());
        if (CollectionUtils.isNotEmpty(configDaos)) {
            return CanaUtility.getErrorMessages(configServiceErrorCode.getConfigTypeDuplicateErrorCode());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(CreateConfigDto createConfigDto) {
        var response = customerServiceVerifier.isUserIdValid(createConfigDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createConfigDto.setCustomDetailDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(GetConfigsByAppIdDto getConfigsByAppIdDto) {
        var response = customerServiceVerifier.isUserIdValid(getConfigsByAppIdDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        getConfigsByAppIdDto.setCustomDetailDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, ConfigDao> isConfigIdValid(Long configId) {
        KeyValue<List<ErrorMessageDto>, ConfigDao> response = new KeyValue<>();
        var configDao = configRepository.findByIdAndActive(configId);
        if (Objects.isNull(configDao)) {
            response.setKey(CanaUtility.getErrorMessages(configServiceErrorCode.getConfigIdNotFound()));
            return response;
        }
        response.setValue(configDao);
        return response;
    }
}
