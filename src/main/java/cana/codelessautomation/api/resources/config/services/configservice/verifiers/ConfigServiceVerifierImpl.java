package cana.codelessautomation.api.resources.config.services.configservice.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.dtos.GetConfigsByUserIdDto;
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

    @Override
    public List<ErrorMessageDto> verifyGetConfigsByUserId(GetConfigsByUserIdDto getConfigsByUserIdDto) {
        return isUserIdValid(getConfigsByUserIdDto);
    }

    @Override
    public List<ErrorMessageDto> verifyCreateConfig(CreateConfigDto createConfigDto) {
        var errors = isUserIdValid(createConfigDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return checkConfigTypeDuplicate(createConfigDto);
    }

    @Override
    public List<ErrorMessageDto> checkConfigTypeDuplicate(CreateConfigDto createConfigDto) {
        if (!Objects.isNull(createConfigDto.getIdentifier()) || createConfigDto.getIdentifier() > 0) {
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
    public List<ErrorMessageDto> isUserIdValid(GetConfigsByUserIdDto getConfigsByUserIdDto) {
        var response = customerServiceVerifier.isUserIdValid(getConfigsByUserIdDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        getConfigsByUserIdDto.setCustomDetailDao(response.getValue());
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
