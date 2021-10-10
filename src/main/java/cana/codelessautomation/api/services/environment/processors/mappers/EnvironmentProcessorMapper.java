package cana.codelessautomation.api.services.environment.processors.mappers;

import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;

public interface EnvironmentProcessorMapper {
    EnvironmentDao mapEnvironmentDao(CreateEnvironmentDto createEnvironment);
}
