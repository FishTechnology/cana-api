package cana.codelessautomation.api.resources.applicationconfig.service.verifiers;

import cana.codelessautomation.api.resources.application.service.verifiers.ApplicationVerifier;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.CreateAppConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.DeleteApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.GetApplicationConfigsDto;
import cana.codelessautomation.api.resources.applicationconfig.service.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.resources.applicationconfig.service.errorcodes.ApplicationConfigErrorCode;
import cana.codelessautomation.api.resources.applicationconfig.service.repositories.ApplicationConfigRepository;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.customer.service.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ApplicationConfigVerifierImpl implements ApplicationConfigVerifier {
    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Inject
    ApplicationConfigRepository appConfigRepository;

    @Inject
    ApplicationConfigErrorCode appConfigErrorCode;

    @Inject
    ApplicationVerifier applicationVerifier;

    @Override
    public List<ErrorMessageDto> verifyCreateApplicationConfig(CreateAppConfigDto createAppConfigDto) {
        var errors = isUserIdValid(createAppConfigDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isApplicationIdValid(createAppConfigDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isKeyValid(createAppConfigDto);
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(CreateAppConfigDto createAppConfigDto) {
        var response = applicationVerifier.isApplicationIdValid(createAppConfigDto.getApplicationId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createAppConfigDto.setApplicationDao(response.getValue());
        return Collections.emptyList();
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
    public List<ErrorMessageDto> verifyGetApplicationConfigs(GetApplicationConfigsDto getApplicationConfigsDto) {
        var errors = isUserIdValid(getApplicationConfigsDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isApplicationIdValid(getApplicationConfigsDto);
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(GetApplicationConfigsDto getApplicationConfigsDto) {
        var response = applicationVerifier.isApplicationIdValid(getApplicationConfigsDto.getApplicationId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        getApplicationConfigsDto.setApplicationDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(GetApplicationConfigsDto getApplicationConfigsDto) {
        var response = customerServiceVerifier.isUserIdValid(getApplicationConfigsDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyDeleteApplicationConfig(DeleteApplicationConfigDto deleteApplicationConfigDto) {
        return isApplicationConfigIdValid(deleteApplicationConfigDto);
    }

    public List<ErrorMessageDto> isApplicationConfigIdValid(DeleteApplicationConfigDto deleteApplicationConfigDto) {
        var applicationConfigDao = appConfigRepository.findByAppConfigId(deleteApplicationConfigDto.getApplicationId(), deleteApplicationConfigDto.getApplicationConfigId());
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
        var applicationConfigDao = appConfigRepository.findByAppConfigId(updateApplicationConfigDto.getApplicationId(), updateApplicationConfigDto.getId());
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
