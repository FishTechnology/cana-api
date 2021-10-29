package cana.codelessautomation.api.services.envvariable;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.dtos.GetEnvVariableByIdDto;
import cana.codelessautomation.api.services.envvariable.dtos.UpdateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;

import java.util.List;

public interface EnvVariableService {
    List<ErrorMessageDto> deleteEnvVariable(DeleteEnvVariableDto deleteEnvVariableDto) throws ValidationException;

    List<ErrorMessageDto> createEnvVariable(CreateEnvVariableDto createEnvVariableDto) throws ValidationException;

    List<EnvironmentVariableDao> getEnvVariables(long environmentId);

    List<ErrorMessageDto> updateEnvVariable(UpdateEnvVariableDto updateEnvVariableDto);

    List<ErrorMessageDto> getEnvVariableById(GetEnvVariableByIdDto getEnvVariableByIdDto);
}
