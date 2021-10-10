package cana.codelessautomation.api.services.environment.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.customer.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.services.environment.errorcodes.EnvironmentErrorCode;
import cana.codelessautomation.api.services.environment.repositories.EnvironmentRepository;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

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

    @Override
    public List<ErrorMessageDto> verifyCreateEnvironment(CreateEnvironmentDto createEnvironmentDto) {
        var errorMessage = isUserIdValid(createEnvironmentDto);
        if (CollectionUtils.isNotEmpty(errorMessage)) {
            return errorMessage;
        }
        return isNameValid(createEnvironmentDto);
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
        if (!response.getKey().isEmpty()) {
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
}
