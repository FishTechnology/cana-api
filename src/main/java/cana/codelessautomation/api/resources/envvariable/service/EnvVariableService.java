package cana.codelessautomation.api.resources.envvariable.service;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.GetEnvVariableByIdDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.UpdateEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableDao;

import java.util.List;

public interface EnvVariableService {
    List<ErrorMessageDto> deleteEnvVariable(DeleteEnvVariableDto deleteEnvVariableDto) throws ValidationException;

    List<ErrorMessageDto> createEnvVariable(CreateEnvVariableDto createEnvVariableDto) throws ValidationException;

    List<EnvironmentVariableDao> getEnvVariables(long environmentId);

    List<ErrorMessageDto> updateEnvVariable(UpdateEnvVariableDto updateEnvVariableDto);

    List<ErrorMessageDto> getEnvVariableById(GetEnvVariableByIdDto getEnvVariableByIdDto);
}
