package cana.codelessautomation.api.services.envvariable.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.customer.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.services.environment.verifiers.EnvironmentVerifier;
import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.errorcodes.EnvironmentVariableErrorCodes;
import cana.codelessautomation.api.services.envvariable.repositories.EnvVariableRepository;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class EnvVariableVerifierImpl implements EnvVariableVerifier {
    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Inject
    EnvVariableRepository envVariableRepository;

    @Inject
    EnvironmentVariableErrorCodes environmentVariableErrorCodes;

    @Inject
    EnvironmentVerifier environmentVerifier;

    @Override
    public List<ErrorMessageDto> verifyGetEnvVariables(long userId) {
        return isUserIdValid(userId);
    }

    @Override
    public List<ErrorMessageDto> verifyDeleteEnvVariable(DeleteEnvVariableDto deleteEnvVariableDto) {
        var errors = isUserIdValid(deleteEnvVariableDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isEnvironmentVariableIdValid(deleteEnvVariableDto);
    }

    @Override
    public List<ErrorMessageDto> verifyCreateEnvVariable(CreateEnvVariableDto createEnvVariableDto) {
        var errors = isEnvironmentIdValid(createEnvVariableDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isUserIdValid(createEnvVariableDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isEnvironmentVariableKeyValid(createEnvVariableDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(CreateEnvVariableDto createEnvVariableDto) {
        var response = customerServiceVerifier.isUserIdValid(createEnvVariableDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createEnvVariableDto.setCustomDetailDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isEnvironmentVariableKeyValid(CreateEnvVariableDto createEnvVariableDto) {
        var environmentVariableDao = envVariableRepository.findByUserIdAndKey(createEnvVariableDto.getUserId(), createEnvVariableDto.getKey());
        if (environmentVariableDao != null) {
            return CanaUtility.getErrorMessages(environmentVariableErrorCodes.getEnvVariableKeyDuplicate());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isEnvironmentIdValid(CreateEnvVariableDto createEnvVariableDto) {
        var response = environmentVerifier.isEnvironmentIdValid(createEnvVariableDto);
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createEnvVariableDto.setEnvironment(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(DeleteEnvVariableDto deleteEnvVariableDto) {
        var response = customerServiceVerifier.isUserIdValid(deleteEnvVariableDto.getUserid());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        deleteEnvVariableDto.setCustomDetailDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isEnvironmentVariableIdValid(DeleteEnvVariableDto deleteEnvVariableDto) {
        var response = isEnvironmentVariableIdValid(deleteEnvVariableDto.getEnvVariableId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }

        if (response.getValue().getUserId() != deleteEnvVariableDto.getUserid()) {
            return CanaUtility.getErrorMessages(environmentVariableErrorCodes.getEnvVarIdNotAssociatedUser());
        }

        deleteEnvVariableDto.setEnvironmentVariableDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, EnvironmentVariableDao> isEnvironmentVariableIdValid(Long envVariableId) {
        KeyValue<List<ErrorMessageDto>, EnvironmentVariableDao> response = new KeyValue<>();
        var environmentVariable = envVariableRepository.findByIdAndIsActive(envVariableId);
        if (environmentVariable == null) {
            response.setKey(CanaUtility.getErrorMessages(environmentVariableErrorCodes.getEnvVariableIdNotFound()));
            return response;
        }
        response.setValue(environmentVariable);
        return response;
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(long userId) {
        var response = customerServiceVerifier.isUserIdValid(userId);
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        return Collections.emptyList();
    }
}
