package cana.codelessautomation.api.services.envvariable.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.dtos.GetEnvVariableByIdDto;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;

import java.util.List;

public interface EnvVariableVerifier {

    List<ErrorMessageDto> isEnvironmentVariableIdValid(GetEnvVariableByIdDto getEnvVariableByIdDto);

    List<ErrorMessageDto> isEnvironmentIdValid(GetEnvVariableByIdDto getEnvVariableByIdDto);

    List<ErrorMessageDto> verifyDeleteEnvVariable(DeleteEnvVariableDto deleteEnvVariableDto);

    List<ErrorMessageDto> isEnvironmentIdValid(long environmentId);

    List<ErrorMessageDto> isUserIdValid(CreateEnvVariableDto createEnvVariableDto);

    List<ErrorMessageDto> isEnvironmentVariableKeyValid(CreateEnvVariableDto createEnvVariableDto);

    List<ErrorMessageDto> isEnvironmentIdValid(CreateEnvVariableDto createEnvVariableDto);

    List<ErrorMessageDto> isUserIdValid(DeleteEnvVariableDto deleteEnvVariableDto);

    List<ErrorMessageDto> isEnvironmentVariableIdValid(DeleteEnvVariableDto deleteEnvVariableDto);

    KeyValue<List<ErrorMessageDto>, EnvironmentVariableDao> isEnvironmentVariableIdValid(Long envVariableId);

    List<ErrorMessageDto> isUserIdValid(long userId);

    List<ErrorMessageDto> isEnvironmentIdValid(DeleteEnvVariableDto deleteEnvVariableDto);

    List<ErrorMessageDto> verifyCreateEnvVariable(CreateEnvVariableDto createEnvVariableDto);

    List<ErrorMessageDto> verifyGetEnvVariableById(GetEnvVariableByIdDto getEnvVariableByIdDto);

    List<ErrorMessageDto> verifyGetEnvVariables(long environmentId);
}
