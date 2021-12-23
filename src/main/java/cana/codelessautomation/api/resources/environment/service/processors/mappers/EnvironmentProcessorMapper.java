package cana.codelessautomation.api.resources.environment.service.processors.mappers;

import cana.codelessautomation.api.resources.environment.service.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;

public interface EnvironmentProcessorMapper {
    EnvironmentDao mapEnvironmentDao(CreateEnvironmentDto createEnvironment);
}
