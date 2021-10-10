package cana.codelessautomation.api.services.envvariable.processors.mappers;

import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;

public interface EnvVariableServiceProcessorMapper {
    EnvironmentVariableDao mapEnvironmentVariableDao(CreateEnvVariableDto createEnvVariableDto);
}
