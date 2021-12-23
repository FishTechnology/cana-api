package cana.codelessautomation.api.resources.envvariable.service.processors.mappers;

import cana.codelessautomation.api.resources.envvariable.service.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableDao;

public interface EnvVariableServiceProcessorMapper {
    EnvironmentVariableDao mapEnvironmentVariableDao(CreateEnvVariableDto createEnvVariableDto);
}
