package cana.codelessautomation.api.resources.envvariable.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.customer.service.dtos.EnvPageSetDetailDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableDao;

import java.util.List;

public interface EnvVariableServiceProcessor {
    EnvPageSetDetailDto processorGetEnvVariables(long userId, int pageNumber, int pageSize);

    List<ErrorMessageDto> processorDeleteEnvVariable(DeleteEnvVariableDto deleteEnvVariableDto);

    List<ErrorMessageDto> processorCreateEnvVariable(CreateEnvVariableDto createEnvVariableDto);

    List<ErrorMessageDto> createEnvVariable(CreateEnvVariableDto createEnvVariableDto);

    List<EnvironmentVariableDao> processorGetEnvVariables(long environmentId);

    List<EnvironmentVariableDao> getEnvVariables(long environmentId);
}
