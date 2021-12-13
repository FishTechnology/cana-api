package cana.codelessautomation.api.services.applicationconfig.verifiers;

import cana.codelessautomation.api.services.applicationconfig.dto.CreateAppConfigDto;
import cana.codelessautomation.api.services.applicationconfig.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.services.applicationconfig.errorcodes.ApplicationConfigErrorCode;
import cana.codelessautomation.api.services.applicationconfig.repositories.ApplicationConfigRepository;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.customer.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@ApplicationScoped
public class ApplicationConfigVerifierImpl implements ApplicationConfigVerifier {
    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Inject
    ApplicationConfigRepository appConfigRepository;

    @Inject
    ApplicationConfigErrorCode appConfigErrorCode;

    @Override
    public List<ErrorMessageDto> verifyCreateApplicationConfig(CreateAppConfigDto createAppConfigDto) {
        var errors = isUserIdValid(createAppConfigDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isKeyValid(createAppConfigDto);
    }

    @Override
    public List<ErrorMessageDto> isKeyValid(CreateAppConfigDto createAppConfigDto) {
        var applicationConfigDaos = appConfigRepository.findByUserIdAndKey(createAppConfigDto.getUserId(), createAppConfigDto.getKey());
        if (CollectionUtils.isEmpty(applicationConfigDaos)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessages(appConfigErrorCode.getKeyDuplicateErrorCode());
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(CreateAppConfigDto createAppConfigDto) {
        var response = customerServiceVerifier.isUserIdValid(Long.valueOf(createAppConfigDto.getUserId()));
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createAppConfigDto.setCustomDetailDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyGetApplicationConfigs(String userId) {
        var response = customerServiceVerifier.isUserIdValid(Long.valueOf(userId));
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyDeleteApplicationConfig(UUID applicationConfigId) {
        return isApplicationConfigIdValid(applicationConfigId);
    }

    @Override
    public List<ErrorMessageDto> isApplicationConfigIdValid(UUID applicationConfigId) {
        var applicationConfigDao = appConfigRepository.findByAppConfigId(applicationConfigId);
        if (Objects.isNull(applicationConfigDao)) {
            return CanaUtility.getErrorMessages(appConfigErrorCode.getAppConfigIdNotFound());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyUpdateApplicationConfig(UpdateApplicationConfigDto updateApplicationConfigDto) {
        var errors = isUserIdValid(updateApplicationConfigDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isApplicationConfigIdValid(updateApplicationConfigDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isKeyValid(updateApplicationConfigDto);
    }

    @Override
    public List<ErrorMessageDto> isKeyValid(UpdateApplicationConfigDto updateApplicationConfigDto) {
        if (StringUtils.isEmpty(updateApplicationConfigDto.getKey())) {
            return Collections.emptyList();
        }

        if (StringUtils.equalsIgnoreCase(updateApplicationConfigDto.getKey(), updateApplicationConfigDto.getApplicationConfigDao().getKey())) {
            return Collections.emptyList();
        }

        var applicationConfigDaos = appConfigRepository.findByUserIdAndKey(updateApplicationConfigDto.getUserId(), updateApplicationConfigDto.getKey());
        if (CollectionUtils.isEmpty(applicationConfigDaos)) {
            return CanaUtility.getErrorMessages(appConfigErrorCode.getKeyDuplicateErrorCode());
        }

        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isApplicationConfigIdValid(UpdateApplicationConfigDto updateApplicationConfigDto) {
        var applicationConfigDao = appConfigRepository.findByAppConfigId(updateApplicationConfigDto.getId());
        if (Objects.isNull(applicationConfigDao)) {
            return CanaUtility.getErrorMessages(appConfigErrorCode.getAppConfigIdNotFound());
        }
        updateApplicationConfigDto.setApplicationConfigDao(applicationConfigDao);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(UpdateApplicationConfigDto updateApplicationConfigDto) {
        var response = customerServiceVerifier.isUserIdValid(Long.valueOf(updateApplicationConfigDto.getUserId()));
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        updateApplicationConfigDto.setCustomDetailDao(response.getValue());
        return Collections.emptyList();
    }
}
