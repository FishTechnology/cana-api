package cana.codelessautomation.api.services.application.verifiers;

import cana.codelessautomation.api.services.application.dtos.CreateApplicationDto;
import cana.codelessautomation.api.services.application.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.services.application.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.services.application.errorcodes.ApplicationErrorCode;
import cana.codelessautomation.api.services.application.repositories.ApplicationRepository;
import cana.codelessautomation.api.services.application.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.customer.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ApplicationVerifierImpl implements ApplicationVerifier {

    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Inject
    ApplicationRepository applicationRepository;

    @Inject
    ApplicationErrorCode applicationErrorCode;

    @Override
    public List<ErrorMessageDto> verifyCreateApplication(CreateApplicationDto createApplicationDto) {
        var errors = isUserIdValid(createApplicationDto);
        if (!errors.isEmpty()) {
            return errors;
        }
        return isNameValid(createApplicationDto);
    }

    @Override
    public List<ErrorMessageDto> isNameValid(CreateApplicationDto createApplicationDto) {
        ApplicationDao applicationDao = applicationRepository.findByNameAndUserId(createApplicationDto.getName(), createApplicationDto.getUserId().longValue());
        if (Objects.isNull(applicationDao)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessages(applicationErrorCode.getDuplicateNameErrorCode());
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(CreateApplicationDto createApplicationDto) {
        var response = customerServiceVerifier.isUserIdValid(Long.valueOf(createApplicationDto.getUserId().longValue()));
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createApplicationDto.setCustomDetailDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyDeleteApplication(DeleteApplicationDto deleteApplicationDto) {
        return isApplicationIdValid(deleteApplicationDto);
    }

    public List<ErrorMessageDto> isApplicationIdValid(DeleteApplicationDto deleteApplicationDto) {
        ApplicationDao applicationDao = applicationRepository.findByAppId(deleteApplicationDto.getApplicationId());
        if (Objects.isNull(applicationDao)) {
            return CanaUtility.getErrorMessages(applicationErrorCode.getAppIdNotFoundErrorCode());
        }
        deleteApplicationDto.setApplicationDao(applicationDao);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyUpdateApplication(UpdateApplicationDto updateApplicationDto) {
        var errors = isUserIdValid(updateApplicationDto);
        if (!errors.isEmpty()) {
            return errors;
        }
        errors = isApplicationIdValid(updateApplicationDto);
        if (!errors.isEmpty()) {
            return errors;
        }
        return isNameValid(updateApplicationDto);
    }

    @Override
    public List<ErrorMessageDto> isNameValid(UpdateApplicationDto updateApplicationDto) {
        if (StringUtils.equalsAnyIgnoreCase(updateApplicationDto.getName(), updateApplicationDto.getApplicationDao().getName())) {
            return Collections.emptyList();
        }

        ApplicationDao applicationDao = applicationRepository.findByNameAndUserId(updateApplicationDto.getName(), updateApplicationDto.getUserId());
        if (Objects.isNull(applicationDao)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessages(applicationErrorCode.getDuplicateNameErrorCode());
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(UpdateApplicationDto updateApplicationDto) {
        var response = isApplicationIdValid(updateApplicationDto.getApplicationId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        updateApplicationDto.setApplicationDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, ApplicationDao> isApplicationIdValid(Long applicationId) {
        KeyValue<List<ErrorMessageDto>, ApplicationDao> response = new KeyValue<>();
        var applicationDao = applicationRepository.findByAppId(applicationId);
        if (applicationDao == null) {
            response.setKey(CanaUtility.getErrorMessages(applicationErrorCode.getAppIdNotFoundErrorCode()));
            return response;
        }
        response.setValue(applicationDao);
        return response;
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(UpdateApplicationDto updateApplicationDto) {
        var response = customerServiceVerifier.isUserIdValid(Long.valueOf(updateApplicationDto.getUserId()));
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        updateApplicationDto.setCustomDetailDao(response.getValue());
        return Collections.emptyList();
    }
}
