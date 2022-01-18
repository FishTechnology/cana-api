package cana.codelessautomation.api.resources.environment.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.environment.service.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.DeleteEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.dtos.UpdateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;

import java.util.List;

public interface EnvironmentVerifier {
    List<ErrorMessageDto> isEnvironmentIdValid(DeleteEnvironmentDto deleteEnvironment);

    List<ErrorMessageDto> isUserIdValid(Long userId);

    List<ErrorMessageDto> isUserIdValid(CreateEnvironmentDto createEnvironmentDto);

    List<ErrorMessageDto> isNameValid(CreateEnvironmentDto createEnvironmentDto);

    List<ErrorMessageDto> verifyCreateEnvironment(CreateEnvironmentDto createEnvironmentDto);

    List<ErrorMessageDto> isApplicationIdValid(CreateEnvironmentDto createEnvironmentDto);

    List<ErrorMessageDto> verifyGetEnvironments(Long userId);

    List<ErrorMessageDto> verifyDeleteEnvironment(DeleteEnvironmentDto deleteEnvironment);

    KeyValue<List<ErrorMessageDto>, EnvironmentDao> isEnvironmentIdValid(Long environmentId);

    List<ErrorMessageDto> verifyUpdateEnvironment(UpdateEnvironmentDto updateEnvironment);

    List<ErrorMessageDto> isNameValid(UpdateEnvironmentDto updateEnvironment);

    List<ErrorMessageDto> isEnvironmentIdValid(UpdateEnvironmentDto updateEnvironment);
}
