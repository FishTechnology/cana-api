package cana.codelessautomation.api.services.envvariable.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.customer.dtos.EnvPageSetDetailDto;
import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.dtos.DeleteEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;

import java.util.List;

public interface EnvVariableServiceProcessor {
    EnvPageSetDetailDto processorGetEnvVariables(long userId, int pageNumber, int pageSize);

    List<ErrorMessageDto> processorDeleteEnvVariable(DeleteEnvVariableDto deleteEnvVariableDto);

    List<ErrorMessageDto> processorCreateEnvVariable(CreateEnvVariableDto createEnvVariableDto);

    List<ErrorMessageDto> createEnvVariable(CreateEnvVariableDto createEnvVariableDto);

    List<EnvironmentVariableDao> processorGetEnvVariables(long environmentId);

    List<EnvironmentVariableDao> getEnvVariables(long environmentId);
}
