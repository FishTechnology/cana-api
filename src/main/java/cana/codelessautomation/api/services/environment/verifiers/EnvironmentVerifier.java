package cana.codelessautomation.api.services.environment.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;

import java.util.List;

public interface EnvironmentVerifier {
    List<ErrorMessageDto> isEnvironmentIdValid(DeleteEnvironmentDto deleteEnvironment);

    List<ErrorMessageDto> isUserIdValid(Long userId);

    List<ErrorMessageDto> isUserIdValid(CreateEnvironmentDto createEnvironmentDto);

    List<ErrorMessageDto> isNameValid(CreateEnvironmentDto createEnvironmentDto);

    List<ErrorMessageDto> verifyCreateEnvironment(CreateEnvironmentDto createEnvironmentDto);

    List<ErrorMessageDto> verifyGetEnvironments(Long userId);

    List<ErrorMessageDto> verifyDeleteEnvironment(DeleteEnvironmentDto deleteEnvironment);

    KeyValue<List<ErrorMessageDto>, EnvironmentDao> isEnvironmentIdValid(Long environmentId);
}
