package cana.codelessautomation.api.resources.environment.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.application.service.verifiers.ApplicationVerifier;
import cana.codelessautomation.api.resources.customer.service.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.resources.environment.service.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.UpdateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.errorcodes.EnvironmentErrorCode;
import cana.codelessautomation.api.resources.environment.service.repositories.EnvironmentRepository;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class EnvironmentVerifierImpl implements EnvironmentVerifier {
    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Inject
    EnvironmentRepository environmentRepository;

    @Inject
    EnvironmentErrorCode environmentErrorCode;

    @Inject
    ApplicationVerifier applicationVerifier;

    @Override
    public List<ErrorMessageDto> verifyCreateEnvironment(CreateEnvironmentDto createEnvironmentDto) {
        var errorMessage = isUserIdValid(createEnvironmentDto);
        if (CollectionUtils.isNotEmpty(errorMessage)) {
            return errorMessage;
        }

        errorMessage = isApplicationIdValid(createEnvironmentDto);
        if (CollectionUtils.isNotEmpty(errorMessage)) {
            return errorMessage;
        }
        return isNameValid(createEnvironmentDto);
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(CreateEnvironmentDto createEnvironmentDto) {
        var response = this.applicationVerifier.isApplicationIdValid(createEnvironmentDto.getApplicationId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createEnvironmentDto.setApplication(createEnvironmentDto.getApplication());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyGetEnvironments(Long userId) {
        return isUserIdValid(userId);
    }

    @Override
    public List<ErrorMessageDto> verifyDeleteEnvironment(DeleteEnvironmentDto deleteEnvironment) {
        return isEnvironmentIdValid(deleteEnvironment);
    }

    @Override
    public List<ErrorMessageDto> isEnvironmentIdValid(DeleteEnvironmentDto deleteEnvironment) {
        var response = isEnvironmentIdValid(deleteEnvironment.getEnvironmentId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }

        deleteEnvironment.setEnvironment(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(Long userId) {
        var response = customerServiceVerifier.isUserIdValid(userId);
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(CreateEnvironmentDto createEnvironmentDto) {
        var response = customerServiceVerifier.isUserIdValid(createEnvironmentDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createEnvironmentDto.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isNameValid(CreateEnvironmentDto createEnvironmentDto) {
        var environmentDao = environmentRepository.findByNameAndUserId(createEnvironmentDto.getName(), createEnvironmentDto.getUserId());
        if (environmentDao != null) {
            return CanaUtility.getErrorMessages(environmentErrorCode.getDuplicateNameFound());
        }
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, EnvironmentDao> isEnvironmentIdValid(Long environmentId) {
        KeyValue<List<ErrorMessageDto>, EnvironmentDao> response = new KeyValue<>();
        var customDetailDao = environmentRepository.findByIdAndActive(environmentId);
        if (customDetailDao == null) {
            response.setKey(CanaUtility.getErrorMessages(environmentErrorCode.getEnvironmentIdNotFound()));
            return response;
        }
        response.setValue(customDetailDao);
        return response;
    }

    @Override
    public List<ErrorMessageDto> verifyUpdateEnvironment(UpdateEnvironmentDto updateEnvironment) {
        var error = isEnvironmentIdValid(updateEnvironment);
        return isNameValid(updateEnvironment);
    }

    @Override
    public List<ErrorMessageDto> isNameValid(UpdateEnvironmentDto updateEnvironment) {
        if (StringUtils.equalsAnyIgnoreCase(updateEnvironment.getName(), updateEnvironment.getEnvironment().getName())) {
            return Collections.emptyList();
        }
        var environmentDao = environmentRepository.findByNameAndUserId(updateEnvironment.getName(), updateEnvironment.getUserId());
        if (environmentDao != null) {
            return CanaUtility.getErrorMessages(environmentErrorCode.getDuplicateNameFound());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isEnvironmentIdValid(UpdateEnvironmentDto updateEnvironment) {
        var response = isEnvironmentIdValid(updateEnvironment.getId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }

        updateEnvironment.setEnvironment(response.getValue());
        return Collections.emptyList();
    }
}
