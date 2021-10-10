package cana.codelessautomation.api.services.environment.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;

import java.util.List;

public interface EnvironmentVerifier {
    List<ErrorMessageDto> isUserIdValid(CreateEnvironmentDto createEnvironmentDto);

    List<ErrorMessageDto> isNameValid(CreateEnvironmentDto createEnvironmentDto);

    KeyValue<List<ErrorMessageDto>, EnvironmentDao> isEnvironmentIdValid(CreateEnvVariableDto createEnvVariableDto);

    List<ErrorMessageDto> verifyCreateEnvironment(CreateEnvironmentDto createEnvironmentDto);
}
